<?xml version="1.0" encoding="UTF-8" ?>

<!-- New XSLT document created with EditiX XML Editor (http://www.editix.com) 
	at Sat Apr 15 14:42:31 CEST 2017 -->

<xsl:stylesheet version="3.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes"
	xmlns:err="http://www.w3.org/2005/xqt-errors" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="xs xdt err fn fo">

	<xsl:output method="xml" indent="yes" />

	<xsl:template match="syntheses">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simpleA4"
					page-height="29.7cm" page-width="21cm" margin-top="2cm"
					margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
					<fo:region-body />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="simpleA4">
				<fo:flow flow-name="xsl-region-body">

					<fo:block text-align="right" line-height="40pt">
						<xsl:variable name="path" select="rootpath" />
						<fo:external-graphic height="50mm" width="40mm"
							content-height="auto" content-width="auto"
							src="url('{$path}/images/logo_onn.jpg')">
						</fo:external-graphic>
						<fo:external-graphic height="5cm" width="4cm"
							content-height="50mm" content-width="40mm"
							src="url('{$path}/images/logo_secaline.png')">
						</fo:external-graphic>
					</fo:block>

					<fo:block font-size="16pt" font-weight="bold" space-after="5mm">
						SYNTHESE INTERVENANTS
						<xsl:value-of select="companyname" />
					</fo:block>

					<fo:block font-size="10pt" font-weight="bold" space-after="5mm">
						<xsl:value-of select="legende" />
					</fo:block>
					<fo:block font-size="10pt">
						<fo:table table-layout="fixed" width="100%"
							border-collapse="separate" border-bottom-width="2pt">
							<fo:table-column column-width="15cm" />
							<fo:table-column column-width="5cm" />

							<fo:table-header>
								<fo:table-row font-weight="bold" text-align="left"
									vertical-align="middle" background-color="#FFFFFF"
									border-bottom-width="2pt">
									<fo:table-cell border-width="2pt" border-bottom="black"
										border-bottom-style="solid" border-bottom-width="2pt">
										<fo:block margin-bottom="5pt">Indicateur</fo:block>
									</fo:table-cell>
									<fo:table-cell border-width="2pt" border-bottom="black"
										border-bottom-style="solid" border-bottom-width="2pt">
										<fo:block margin-bottom="5pt">Valeur</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-header>

							<fo:table-body>
								<xsl:apply-templates select="synthese" />
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template match="synthese">
		<fo:table-row>
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="description" />
				</fo:block>
			</fo:table-cell>

			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="valeur" />
				</fo:block>
			</fo:table-cell>
		</fo:table-row>
	</xsl:template>

</xsl:stylesheet>
