package org.onn.webportal.domain.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.domain.model.Syntheses;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.domain.service.ExportService;
import org.onn.webportal.infra.repository.MetadataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ExportServiceImpl implements ExportService {

	@Autowired
	private ActiviteService activiteService;

	public ModelAndView exportSynthese(String code, String typeLocalisation, String codeIntervenant, String annee, String legende, HttpServletResponse response, HttpServletRequest request) {
		if(code.equals("")) return null;

		Integer anneeVal;
		try{
			anneeVal = Integer.valueOf(annee);
		}catch (Exception e) {
			anneeVal = Calendar.getInstance().get(Calendar.YEAR);
		}

		//String legende = "Pays: Madagascar \n Region: Analamanga \n Commune: Tana";
		List<Synthese> synthReses = activiteService.getActiviteSyntese(code, TypeLocalisation.getByValue(typeLocalisation), codeIntervenant, anneeVal);
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Syntheses.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			Syntheses syntheses = new Syntheses();
			syntheses.setSyntheses(synthReses);
			syntheses.setLegende(legende);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(syntheses, baos);
			//create input stream from baos
			InputStream isFromFirstData = new ByteArrayInputStream(baos.toByteArray()); 
			convertToPDF(isFromFirstData, response, request);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FOPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void convertToPDF(InputStream inputStreamData, HttpServletResponse response, HttpServletRequest request)  throws IOException, FOPException, TransformerException {
		// the XSL FO file
		//File xsltFile = new File("/Users/tantely/Documents/ETUDES/SOA/workspace/onn/src/main/webapp/export/synthese_export.xsl");
		File xsltFile = new File(request.getRealPath("export/synthese_export.xsl"));
		// the XML file which provides the input
		StreamSource xmlSource = new StreamSource(inputStreamData);
		// create an instance of fop factory
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		// a user agent is needed for transformation
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		// Setup output
		//File outFile = new File("/Users/tantely/Documents/ETUDES/SOA/workspace/onn/src/main/webapp/export/temp/synthese.pdf");
		//OutputStream out = new java.io.FileOutputStream(outFile,false);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			// Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

			// Resulting SAX events (the generated FO) must be piped through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Start XSLT transformation and FOP processing
			// That's where the XML is first transformed to XSL-FO and then 
			// PDF is created
			transformer.transform(xmlSource, res);
			
			//Prepare response
			response.setContentType("application/pdf");
			String name = "export_synthese";
			response.setHeader("Content-disposition", "inline;filename=" + name + ".pdf");
			response.setContentLength(out.size());

			//Send content to Browser
			response.getOutputStream().write(out.toByteArray());
			response.getOutputStream().flush();
			
		} finally {
			out.close();
		}
	}


}
