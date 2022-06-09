package com.jamar.apc.persistence.adapter.impl;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jamar.apc.persistence.adapter.port.IAPCPersistenseAdapter;
import com.jamar.apc.persistence.entities.Cencanceladas;
import com.jamar.apc.persistence.entities.Cenclientesconsultar;
import com.jamar.apc.persistence.entities.Cendetalle;
import com.jamar.apc.persistence.entities.Cendetalles;
import com.jamar.apc.persistence.entities.Cenestatus;
import com.jamar.apc.persistence.entities.Cengenerales;
import com.jamar.apc.persistence.entities.Cenmovimientos;
import com.jamar.apc.persistence.entities.Cenreferenciascanceladas;
import com.jamar.apc.persistence.entities.Cenresumen;
import com.jamar.apc.persistence.entities.Cenresumenes;
import com.jamar.apc.persistence.entities.Censcore;
import com.jamar.apc.persistence.entities.Cenvalidacion;
import com.jamar.apc.util.AdapterUtil;
import com.ssasis.apc.encrypt.EncryptDecrypt;
import com.ssasis.apc.encrypt.EncryptDecryptException;

@Component
@Configuration
@PropertySource("classpath:/META-INF/messages.properties")
public class APCPersistenseAdapter implements IAPCPersistenseAdapter {

	@Override
	public Cenclientesconsultar getConsultaFromServiceResponse(String serviceResponse) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Cenclientesconsultar consulta = new Cenclientesconsultar();
		List<Cendetalle> detalles = null;
		List<Cenresumen> resumenes = null;
		List<Cenreferenciascanceladas> referencias = null;
		List<Cenmovimientos> movimientos = null;
		List<Cengenerales> generales = null;
		List<Censcore> scores = null;
		Cenvalidacion validacion = null;
		Cenestatus estatus = null;
		Document response;
		try {
			EncryptDecrypt decrypt = new EncryptDecrypt();
			builder = factory.newDocumentBuilder();
			response = builder.parse(new InputSource(new StringReader(serviceResponse)));
			response.getDocumentElement().normalize();
			NodeList listaElementos = response.getChildNodes();
			for (int i = 0; i < listaElementos.getLength(); i++) {
				Node nodo = listaElementos.item(i);
				if (nodo.getNodeName().equals("Resultado")) {
					NodeList hijosResultado = nodo.getChildNodes();
					for (int l = 0; l < hijosResultado.getLength(); l++) {
						Node nodoHijo = hijosResultado.item(l);
						if (nodoHijo.getNodeName().equals("Detalle")) {
							detalles = getDetalles(nodoHijo, decrypt, consulta);
						} else if (nodoHijo.getNodeName().equals("Resumen")) {
							resumenes = getResumenes(nodoHijo, decrypt, consulta);
						} else if (nodoHijo.getNodeName().equals("ReferenciasCanceladas")) {
							referencias = getReferenciasCanceladas(nodoHijo, decrypt, consulta);
						} else if (nodoHijo.getNodeName().equals("Movimientos")) {
							movimientos = getMovimientos(nodoHijo, decrypt, consulta);
						} else if (nodoHijo.getNodeName().equals("Generales")) {
							generales = getGenerales(nodoHijo, decrypt, consulta);
						} else if (nodoHijo.getNodeName().equals("Validacion")) {
							validacion = getValidacion(nodoHijo, consulta);
						} else if (nodoHijo.getNodeName().equals("Estatus")) {
							estatus = getEstatus(nodoHijo, consulta);
						} else if (nodoHijo.getNodeName().equals("Score")) {
							scores = getScores(nodoHijo, decrypt, consulta);
						}
					}
				}
			}
			List<Cendetalles> listaDetalles = new ArrayList<Cendetalles>();
			Cendetalles detallesObjeto = new Cendetalles();
			for (Cendetalle detalleLocal : detalles) {
				detalleLocal.setIddetalles(detallesObjeto);
			}
			detallesObjeto.setDetalles(detalles);
			detallesObjeto.setId(consulta);
			listaDetalles.add(detallesObjeto);

			List<Cenresumenes> listaResumenes = new ArrayList<Cenresumenes>();
			Cenresumenes resumenesObject = new Cenresumenes();
			resumenesObject.setId(consulta);
			for (Cenresumen resumenLocal : resumenes) {
				resumenLocal.setIdresumenes(resumenesObject);
			}
			resumenesObject.setResumenes(resumenes);
			listaResumenes.add(resumenesObject);

			List<Cencanceladas> listaRefCanceladas = new ArrayList<Cencanceladas>();
			Cencanceladas canceladasObject = new Cencanceladas();
			canceladasObject.setId(consulta);
			for (Cenreferenciascanceladas referenciaLocal : referencias) {
				referenciaLocal.setIdcanceladas(canceladasObject);
			}
			canceladasObject.setReferenciasCanceladas(referencias);
			listaRefCanceladas.add(canceladasObject);

			List<Cenvalidacion> validacionesList = new ArrayList<Cenvalidacion>();
			validacionesList.add(validacion);

			List<Cenestatus> estatusList = new ArrayList<Cenestatus>();
			estatusList.add(estatus);

			consulta.setEstatus(estatusList);
			consulta.setValidacion(validacionesList);
			consulta.setResumenes(listaResumenes);
			consulta.setDetalles(listaDetalles);
			consulta.setMovimientos(movimientos);
			consulta.setGenerales(generales);
			consulta.setReferenciasCanceladas(listaRefCanceladas);
			consulta.setScores(scores);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptDecryptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return consulta;
	}

	public Cenestatus getEstatus(Node detalleNode, Cenclientesconsultar consulta) {
		Cenestatus estatus = new Cenestatus();
		if (detalleNode.getFirstChild() != null && detalleNode.getFirstChild().getNodeValue() != null) {
			if (!detalleNode.getFirstChild().getNodeValue().equals("")) {
				estatus.setEstatus(Short.parseShort(detalleNode.getFirstChild().getNodeValue()));
			}

			estatus.setId(consulta);
		}

		return estatus;
	}

	public Cenvalidacion getValidacion(Node detalleNode, Cenclientesconsultar consulta)
			throws DOMException, EncryptDecryptException {
		Cenvalidacion validacion = new Cenvalidacion();
		Node esValido = detalleNode.getFirstChild();
		if (esValido.getFirstChild() != null && esValido.getFirstChild().getNodeValue() != null) {
			if (!esValido.getFirstChild().getNodeValue().equals("")) {
				validacion.setEsValido(Short.parseShort(esValido.getFirstChild().getNodeValue()));
			}
			validacion.setId(consulta);
		}
		return validacion;
	}

	public List<Cengenerales> getGenerales(Node detalleNode, EncryptDecrypt decrypt, Cenclientesconsultar consulta)
			throws DOMException, EncryptDecryptException {
		List<Cengenerales> generalesList = new ArrayList<Cengenerales>();
		NodeList detallesNode = detalleNode.getChildNodes();
		Cengenerales general;
		for (int i = 0; i < detallesNode.getLength(); i++) {
			NodeList elementosDetalle = detallesNode.item(i).getChildNodes();
			general = new Cengenerales();
			general.setId(consulta);
			for (int k = 0; k < elementosDetalle.getLength(); k++) {
				Node elementoDetalle = elementosDetalle.item(k);
				if (elementoDetalle.getNodeName().equals("NOMBRE")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						general.setNombre(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}

				} else if (elementoDetalle.getNodeName().equals("APELLIDO")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						general.setApellido(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("IDENT_CLIE")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						general.setIdentClie(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("FEC_CREACION")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						general.setFecCreacion(AdapterUtil
								.stringToDate(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
					}
				} else if (elementoDetalle.getNodeName().equals("NOM_ASOC")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						general.setNomAsoc(StringEscapeUtils
								.unescapeHtml(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
					}
				} else if (elementoDetalle.getNodeName().equals("FEC_DEFUNCION")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						general.setFecDefuncion(AdapterUtil
								.stringToDate(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
					}
				}

			}
			generalesList.add(general);
		}
		return generalesList;
	}

	public List<Cenmovimientos> getMovimientos(Node detalleNode, EncryptDecrypt decrypt, Cenclientesconsultar consulta)
			throws DOMException, EncryptDecryptException {
		List<Cenmovimientos> movimientosList = new ArrayList<Cenmovimientos>();
		NodeList detallesNode = detalleNode.getChildNodes();
		Cenmovimientos movimiento;
		for (int i = 0; i < detallesNode.getLength(); i++) {
			NodeList elementosDetalle = detallesNode.item(i).getChildNodes();
			movimiento = new Cenmovimientos();
			movimiento.setId(consulta);
			for (int k = 0; k < elementosDetalle.getLength(); k++) {
				Node elementoDetalle = elementosDetalle.item(k);
				if (elementoDetalle.getNodeName().equals("NOM_ASOC")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						movimiento.setNomAsoc(StringEscapeUtils
								.unescapeHtml((decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()))));
					}
				} else if (elementoDetalle.getNodeName().equals("FEC1")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						movimiento.setFec1(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("IDENT_CLIE")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						movimiento.setIdentClie(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				}

			}
			movimientosList.add(movimiento);
		}
		return movimientosList;

	}

	public List<Censcore> getScores(Node detalleNode, EncryptDecrypt decrypt, Cenclientesconsultar consulta)
			throws DOMException, EncryptDecryptException {
		List<Censcore> scoresList = new ArrayList<Censcore>();
		NodeList detallesNode = detalleNode.getChildNodes();
		Censcore score;
		for (int i = 0; i < detallesNode.getLength(); i++) {
			NodeList elementosDetalle = detallesNode.item(i).getChildNodes();
			score = new Censcore();
			score.setId(consulta);
			for (int k = 0; k < elementosDetalle.getLength(); k++) {
				Node elementoDetalle = elementosDetalle.item(k);
				if (elementoDetalle.getNodeName().equals("PI")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						if (!decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()).equals("")) {
							score.setPi(
									new BigDecimal(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
						}
					}
				} else if (elementoDetalle.getNodeName().equals("SCORE")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						if (!decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()).equals("")) {
							score.setScore(
									Long.parseLong(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
						}

					}
				} else if (elementoDetalle.getNodeName().equals("EXCLUSION")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						score.setExclusion(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				}

			}
			scoresList.add(score);
		}
		return scoresList;

	}

	public List<Cenresumen> getResumenes(Node detalleNode, EncryptDecrypt decrypt, Cenclientesconsultar consulta)
			throws DOMException, EncryptDecryptException {
		List<Cenresumen> resumenesList = new ArrayList<Cenresumen>();
		NodeList detallesNode = detalleNode.getChildNodes();
		Cenresumen resumen;
		for (int i = 0; i < detallesNode.getLength(); i++) {
			NodeList elementosDetalle = detallesNode.item(i).getChildNodes();
			resumen = new Cenresumen();
			resumen.setId(consulta);
			for (int k = 0; k < elementosDetalle.getLength(); k++) {
				Node elementoDetalle = elementosDetalle.item(k);
				if (elementoDetalle.getNodeName().equals("RELACION")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						resumen.setRelacion(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
						;
					}

				} else if (elementoDetalle.getNodeName().equals("CANTIDAD")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						if (!decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()).equals("")) {
							resumen.setCantidad(
									Integer.parseInt(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
						}

					}
				} else if (elementoDetalle.getNodeName().equals("MONTO")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {

						if (!decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()).equals("")) {
							resumen.setMonto(
									new BigDecimal(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));

						}

					}
				} else if (elementoDetalle.getNodeName().equals("SALDO_ACTUAL")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						if (!decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()).equals("")) {
							resumen.setSaldoActual(
									new BigDecimal(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
						}
					}
				}

			}
			resumenesList.add(resumen);
		}
		return resumenesList;

	}

	public List<Cenreferenciascanceladas> getReferenciasCanceladas(Node detalleNode, EncryptDecrypt decrypt,
			Cenclientesconsultar consulta) throws DOMException, EncryptDecryptException {
		List<Cenreferenciascanceladas> referenciasList = new ArrayList<Cenreferenciascanceladas>();
		NodeList detallesNode = detalleNode.getChildNodes();
		Cenreferenciascanceladas referencia;
		for (int i = 0; i < detallesNode.getLength(); i++) {
			NodeList elementosDetalle = detallesNode.item(i).getChildNodes();
			referencia = new Cenreferenciascanceladas();
			referencia.setId(consulta);
			for (int k = 0; k < elementosDetalle.getLength(); k++) {
				Node elementoDetalle = elementosDetalle.item(k);
				if (elementoDetalle.getNodeName().equals("NOM_ASOC")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						referencia.setNomAsoc(StringEscapeUtils
								.unescapeHtml(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
					}

				} else if (elementoDetalle.getNodeName().equals("DESCR_CORTA_RELA")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						referencia.setDescrCortaRela(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("FEC_INICIO_REL")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						referencia.setFecInicioRel(AdapterUtil
								.stringToDate(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
					}
				} else if (elementoDetalle.getNodeName().equals("FEC_FIN_REL")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						referencia.setFecFinRel(AdapterUtil
								.stringToDate(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
					}
				} else if (elementoDetalle.getNodeName().equals("MONTO_ORIGINAL")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						if (!decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()).equals("")) {
							referencia.setMontoOriginal(
									new BigDecimal(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
						}
					}
				} else if (elementoDetalle.getNodeName().equals("FEC_LIQUIDACION")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						referencia.setFecLiquidacion(AdapterUtil
								.stringToDate(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
					}
				} else if (elementoDetalle.getNodeName().equals("HISTORIA")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						referencia.setHistoria(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("DESCR_OBS_CORTA")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						referencia.setDescrObsCorta(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("MONTO_CODIFICADO")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						referencia.setMontoCodificado(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("COD_GRUPO_ECON")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						referencia.setCodGrupoEcon(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("TIPO_ASOC")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						referencia.setTipoAsoc(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("NUM_REFER")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						referencia.setNumRefer(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				}

			}
			referenciasList.add(referencia);
		}
		return referenciasList;
	}

	public List<Cendetalle> getDetalles(Node detalleNode, EncryptDecrypt decrypt, Cenclientesconsultar consulta)
			throws DOMException, EncryptDecryptException {
		List<Cendetalle> detallesList = new ArrayList<Cendetalle>();
		NodeList detallesNode = detalleNode.getChildNodes();
		Cendetalle detalle;
		for (int i = 0; i < detallesNode.getLength(); i++) {
			NodeList elementosDetalle = detallesNode.item(i).getChildNodes();
			detalle = new Cendetalle();
			detalle.setId(consulta);
			for (int k = 0; k < elementosDetalle.getLength(); k++) {
				Node elementoDetalle = elementosDetalle.item(k);
				if (elementoDetalle.getNodeName().equals("NOM_ASOC")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setNomAsoc(StringEscapeUtils
								.unescapeHtml(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
					}
				} else if (elementoDetalle.getNodeName().equals("DESCR_CORTA_RELA")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setDescrCortaRela(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("FEC_INICIO_REL")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setFecInicioRel(AdapterUtil
								.stringToDate(((decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())))));
					}
				} else if (elementoDetalle.getNodeName().equals("FEC_FIN_REL")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setFecFinRel(AdapterUtil
								.stringToDate(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
					}
				} else if (elementoDetalle.getNodeName().equals("MONTO_ORIGINAL")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						if (!decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()).equals("")) {
							detalle.setMontoOriginal(
									new BigDecimal(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
						}
					}
				} else if (elementoDetalle.getNodeName().equals("NUM_PAGOS")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						if (!decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()).equals("")) {
							detalle.setNumPagos(
									Short.parseShort(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
						}
					}
				} else if (elementoDetalle.getNodeName().equals("DESCR_FORMA_PAGO")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setDescrFormaPago(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("IMPORTE_PAGO")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						if (!decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()).equals("")) {
							detalle.setImportePago(
									new BigDecimal(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
						}
					}
				} else if (elementoDetalle.getNodeName().equals("FEC_ULTIMO_PAGO")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setFecUltimoPago(AdapterUtil
								.stringToDate(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
					}
				} else if (elementoDetalle.getNodeName().equals("MONTO_ULTIMO_PAGO")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						if (!decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()).equals("")) {
							detalle.setMontoUltimoPago(
									new BigDecimal(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
						}
					}
				} else if (elementoDetalle.getNodeName().equals("DESCR_OBS_CORTA")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setDescrObsCorta(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("SALDO_ACTUAL")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						if (!decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()).equals("")) {
							detalle.setSaldoActual(
									new BigDecimal(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
						}
					}
				} else if (elementoDetalle.getNodeName().equals("NUM_DIAS_ATRASO")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setNumDiasAtraso(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("HISTORIA")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setHistoria(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("MONTO_CODIFICADO")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setMontoCodificado(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("FEC_ACTUALIZACION")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setFecActualizacion(AdapterUtil
								.stringToDate(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue())));
					}
				} else if (elementoDetalle.getNodeName().equals("COD_GRUPO_ECON")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setCodGrupoEcon(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("TIPO_ASOC")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setTipoAsoc(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				} else if (elementoDetalle.getNodeName().equals("NUM_REFER")) {
					if (elementoDetalle.getFirstChild() != null
							&& elementoDetalle.getFirstChild().getNodeValue() != null) {
						detalle.setNumRefer(decrypt.decrypt(elementoDetalle.getFirstChild().getNodeValue()));
					}
				}

			}
			detallesList.add(detalle);
		}
		return detallesList;
	}

}
