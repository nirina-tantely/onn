package org.onn.webportal.domain.service.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.application.utils.CSVUtils;
import org.onn.webportal.domain.model.ExportONGBase;
import org.onn.webportal.domain.model.ExportONGBaseList;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.domain.model.Syntheses;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.domain.service.ExportService;
import org.onn.webportal.infra.repository.MetadataRepo;
import org.onn.webportal.presentation.controller.ExportController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ExportServiceImpl implements ExportService {

	private final Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);

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
			convertToPDF("export_synthese", "synthese_export.xsl", isFromFirstData, response, request);
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


	public void exportONGBase(String code, String typeLocalisation, String annee, String legende, HttpServletResponse response, HttpServletRequest request, boolean tousIndicateurs) {
		if(code.equals("")) return ;

		Integer anneeVal;
		try{
			anneeVal = Integer.valueOf(annee);
		}catch (Exception e) {
			anneeVal = Calendar.getInstance().get(Calendar.YEAR);
		}


		JSONArray indicateurs = activiteService.getONGBaseSyntese(code, TypeLocalisation.getByValue(typeLocalisation), anneeVal, tousIndicateurs);
		List<ExportONGBase> exportList = new ArrayList<ExportONGBase>();
		for (int i = 0; i < indicateurs.size(); i++) {
			JSONObject jsonobject = (JSONObject) indicateurs.get(i);
			String indicateur = (String) jsonobject.get("indicateur");
			String t1 = (String) jsonobject.get("T1");
			String t2 = (String) jsonobject.get("T2");
			String t3 = (String) jsonobject.get("T3");
			String t4 = (String) jsonobject.get("T4");
			ExportONGBase export = new ExportONGBase();
			export.setIndicateur(indicateur);
			export.setT1(t1);
			export.setT2(t2);
			export.setT3(t3);
			export.setT4(t4);
			exportList.add(export);
		}

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ExportONGBaseList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ExportONGBaseList liste = new ExportONGBaseList();
			liste.setExportONGBase(exportList);
			liste.setLegende(legende);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(liste, baos);
			//create input stream from baos
			InputStream isFromFirstData = new ByteArrayInputStream(baos.toByteArray()); 
			convertToPDF("export_ongbase", "ongbase_export.xsl", isFromFirstData, response, request);
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
	}

	public void convertToPDF(String filename, String xslFile, InputStream inputStreamData, HttpServletResponse response, HttpServletRequest request)  throws IOException, FOPException, TransformerException {
		// the XSL FO file
		//File xsltFile = new File("/Users/tantely/Documents/ETUDES/SOA/workspace/onn/src/main/webapp/export/synthese_export.xsl");
		File xsltFile = new File(request.getRealPath("export/"+xslFile));
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
			String name = filename;
			response.setHeader("Content-disposition", "attachment;filename=" + name + ".pdf");
			response.setContentLength(out.size());

			//Send content to Browser
			response.getOutputStream().write(out.toByteArray());
			response.getOutputStream().flush();

		} finally {
			out.close();
		}
	}

	public ModelAndView exportSyntheseCSV(String code, String typeLocalisation, String codeIntervenant, String annee, HttpServletResponse response, HttpServletRequest request) {
		if(code.equals("")) return null;

		Integer anneeVal;
		try{
			anneeVal = Integer.valueOf(annee);
		}catch (Exception e) {
			anneeVal = Calendar.getInstance().get(Calendar.YEAR);
		}

		//String legende = "Pays: Madagascar \n Region: Analamanga \n Commune: Tana";
		List<Synthese> synthReses = activiteService.getActiviteSyntese(code, TypeLocalisation.getByValue(typeLocalisation), codeIntervenant, anneeVal);

		List<String> listeEntete = new ArrayList<String>();
		List<String> listeValeur = new ArrayList<String>();
		for (Synthese synthese : synthReses) {
			listeEntete.add(synthese.getDescription());
			listeValeur.add(String.valueOf(synthese.getValeur()));
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Writer writer = new BufferedWriter(new OutputStreamWriter(out));
		try {
			CSVUtils.writeLine(writer, listeEntete, ';');
			CSVUtils.writeLine(writer, listeValeur, ';');
			writer.flush();
		} catch (IOException e1) {
			logger.error(e1.getMessage());
		}

		try {
			//Prepare response
			response.setContentType("text/csv");
			String name = "Synthese_activite";
			response.setHeader("Content-disposition", "attachment;filename=" + name + ".csv");
			response.setHeader("Content-Type", "text/csv; charset=UTF-8");
			response.setContentLength(out.size());

			//System.out.println("==> "+out.toString());
			//Send content to Browser
			response.getOutputStream().write(out.toByteArray());
			response.getOutputStream().flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	public void exportONGBaseCSV(String code, String typeLocalisation, String annee, HttpServletResponse response, HttpServletRequest request, boolean tousIndicateurs) {
		if(code.equals("")) return ;

		Integer anneeVal;
		try{
			anneeVal = Integer.valueOf(annee);
		}catch (Exception e) {
			anneeVal = Calendar.getInstance().get(Calendar.YEAR);
		}


		JSONArray indicateurs = activiteService.getONGBaseSyntese(code, TypeLocalisation.getByValue(typeLocalisation), anneeVal, tousIndicateurs);

		List<String> listeEntete = new ArrayList<String>();
		listeEntete.add("Trimestre");
		List<String> listeT1 = new ArrayList<String>();
		List<String> listeT2 = new ArrayList<String>();
		List<String> listeT3 = new ArrayList<String>();
		List<String> listeT4 = new ArrayList<String>();
		listeT1.add("T1");
		listeT2.add("T2");
		listeT3.add("T3");
		listeT4.add("T4");
		for (int i = 0; i < indicateurs.size(); i++) {
			JSONObject jsonobject = (JSONObject) indicateurs.get(i);
			String indicateur = (String) jsonobject.get("indicateur");
			String t1 = (String) jsonobject.get("T1");
			String t2 = (String) jsonobject.get("T2");
			String t3 = (String) jsonobject.get("T3");
			String t4 = (String) jsonobject.get("T4");
			listeEntete.add(indicateur);
			listeT1.add(t1);
			listeT2.add(t2);
			listeT3.add(t3);
			listeT4.add(t4);
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Writer writer = new BufferedWriter(new OutputStreamWriter(out));
		try {
			CSVUtils.writeLine(writer, listeEntete, ';');
			CSVUtils.writeLine(writer, listeT1, ';');
			CSVUtils.writeLine(writer, listeT2, ';');
			CSVUtils.writeLine(writer, listeT3, ';');
			CSVUtils.writeLine(writer, listeT4, ';');
			writer.flush();
		} catch (IOException e1) {
			logger.error(e1.getMessage());
		}

		try {
			//Prepare response
			response.setContentType("text/csv");
			String name = "ONGBASE_export";
			response.setHeader("Content-disposition", "attachment;filename=" + name + ".csv");
			response.setHeader("Content-Type", "text/csv; charset=UTF-8");
			response.setContentLength(out.size());

			//System.out.println("==> "+out.toString());
			//Send content to Browser
			response.getOutputStream().write(out.toByteArray());
			response.getOutputStream().flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
