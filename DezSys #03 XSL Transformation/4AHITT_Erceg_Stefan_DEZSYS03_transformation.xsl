<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

<xsl:template match="/klassenliste">
  <html>
  <body>
	<h2>Klassenliste</h2>
	<xsl:apply-templates select="klasse/@jahrgang"/><br/>
	<xsl:for-each select="/klassenliste/klasse">
		<h3><xsl:value-of select="@jahrgang"/></h3>
		<table border="1">
			<tr bgcolor="#9acd32">
				<th>Katalognummer</th>
				<th>Vorname</th>
				<th>Nachname</th>
				<th>Geschlecht</th>
				<th>Geburtsdatum</th>
				<th>Religionsbekenntnis</th>
				<th>Telefonnummer</th>
			</tr>
			<xsl:for-each select="schueler">
				<xsl:sort select="@katalognummer"/>
				<xsl:if test="geschlecht='männlich'">
					<tr>
						<td><xsl:value-of select="@katalognummer"/></td>
						<td><xsl:value-of select="vorname"/></td>
						<td><xsl:value-of select="nachname"/></td>
						<td><xsl:value-of select="geschlecht"/></td>
						<td><xsl:value-of select="geburtsdatum"/></td>
						<xsl:choose>
							<xsl:when test="religionsbekenntnis='röm.kath.'">
								<td bgcolor='#ff00ff'>
								<xsl:value-of select="religionsbekenntnis"/>
								</td>
							</xsl:when>
							<xsl:otherwise>
								<td>
								<xsl:value-of select="religionsbekenntnis"/>
								</td>
							</xsl:otherwise>
						</xsl:choose>
						<td><xsl:value-of select="telefonnummer"/></td>
					</tr>
				</xsl:if>
			</xsl:for-each>
		</table>
	</xsl:for-each>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>