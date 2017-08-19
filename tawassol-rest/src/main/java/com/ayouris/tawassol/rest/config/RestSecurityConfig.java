package com.ayouris.tawassol.rest.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ayouris.tawassol.resource.*;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import com.ayouris.tawassol.common.mapper.CustomJsonObjectMapper;
import com.ayouris.tawassol.exception.BackendExceptionMapper;
import com.ayouris.tawassol.security.resource.impl.AuthenticateResourceImpl;
import com.ayouris.tawassol.security.resource.impl.UserResourceImpl;

@Configuration
public class RestSecurityConfig {

	@Autowired
	private AuthenticateResourceImpl authenticateRestService;
	@Autowired
	private UserResourceImpl userResource;

	@Autowired
	private SiteResource siteResource;

	@Autowired
	private BanqueResource banqueResource;
	@Autowired
	private GroupResource groupResource;
	@Autowired
	private ClientResource clientResource;
	@Autowired
	private ConcessionnaireResource concessionnaireResource;
	@Autowired
	private ChauffeurResource chauffeurResource;
	@Autowired
	private CamionResource camionResource;
	@Autowired
	private MarqueResource marqueResource;
	@Autowired
	private ProduitResource produitResource;
	@Autowired
	private TransporteurResource transporteurResource;
	@Autowired
	private FournisseurResource fournisseurResource;
	@Autowired
	private PrestationResource prestationResource;
	@Autowired
	private TypeFileAttenteResource typeFileAttenteResource;
	@Autowired
	private ControleCamionResource controleCamionResource;
	@Autowired
	private AffectationResource affectationResource;
	@Autowired
	private SignataireResource signataireResource;
	@Autowired
	private PieceReglementResource pieceReglementResource;
	@Autowired
	private CiterneResource citerneResource;
	@Autowired
	private FabriquantResource fabriquantResource;
	@Autowired
	private AffectationProduitOperateurResource affectationProduitOperateurResource;
	@Autowired
	private FileAttenteResource fileAttenteResource;

	@Autowired
	private JourneeActiviteResource journeeActiviteResource;

	@Autowired
	private OrdreLivraisonResource ordreLivraisonResource;
	@Autowired
	private DestinationResource destinationResource;

	@Autowired
	private EnumResource enumResource;

	@Autowired
	private BonLivraisonResource bonLivraisonResource;

	@Autowired
	private EmplissageResource emplissageResource;

	@Autowired
	private PrixVenteClientResource prixVenteClientResource;
	@Autowired
	private MotifResource motifResource;
	@Autowired
	private DateResource dateResource;
	@Autowired
	private FicheMarcheResource ficheMarcheResource;

	@Autowired
	private CircuitDerogationResource circuitDerogationResource;

	@Autowired
	private DelegationResource delegationResource;

	@Autowired
	private PrixVenteConcessionnaireResource prixVenteConcessionnaireResource;

	@Autowired
	private UsersResource usersResource;

	@Autowired
	private ProfilResource profilResource;

	@Autowired
	private TypePieceReglementResource typePieceReglementResource;

	@Autowired
	private ConditionCommercialeResource conditionCommercialeResource;

	final private Map<Object, Object> extensionMappings = new HashMap<Object, Object>();

	final private List<Interceptor<? extends Message>> inInterseptors = new ArrayList<Interceptor<? extends Message>>();
	final private List<Interceptor<? extends Message>> outInterseptors = new ArrayList<Interceptor<? extends Message>>();

	@Bean
	public ServletRegistrationBean cxfServlet() {
		final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(),
				"/tawassol/*");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

	@Bean(destroyMethod = "shutdown")
	public SpringBus cxf() {
		return new SpringBus();
	}

	@Bean(destroyMethod = "destroy")
	@DependsOn("cxf")
	public Server jaxRsServer() {

		JAXRSServerFactoryBean jAXRSServerFactoryBean = getJAXRSServerFactoryBean();
		jAXRSServerFactoryBean.setAddress("/service");
		jAXRSServerFactoryBean.setServiceBean(authenticateRestService);
		jAXRSServerFactoryBean.setServiceBean(userResource);
		// jAXRSServerFactoryBean.setServiceBean(organizationRestService);
		// jAXRSServerFactoryBean.setServiceBean(permissionRestService);
		// jAXRSServerFactoryBean.setServiceBean(roleRestService);
		// jAXRSServerFactoryBean.setServiceBean(userRestService);
		
		jAXRSServerFactoryBean.setServiceBean(siteResource);
		jAXRSServerFactoryBean.setServiceBean(enumResource);
		jAXRSServerFactoryBean.setServiceBean(banqueResource);
		jAXRSServerFactoryBean.setServiceBean(groupResource);
		jAXRSServerFactoryBean.setServiceBean(clientResource);
		jAXRSServerFactoryBean.setServiceBean(chauffeurResource);
		jAXRSServerFactoryBean.setServiceBean(concessionnaireResource);
		jAXRSServerFactoryBean.setServiceBean(camionResource);
		jAXRSServerFactoryBean.setServiceBean(marqueResource);
		jAXRSServerFactoryBean.setServiceBean(produitResource);
		jAXRSServerFactoryBean.setServiceBean(transporteurResource);
		jAXRSServerFactoryBean.setServiceBean(fournisseurResource);
		jAXRSServerFactoryBean.setServiceBean(typeFileAttenteResource);
		jAXRSServerFactoryBean.setServiceBean(prestationResource);
		jAXRSServerFactoryBean.setServiceBean(controleCamionResource);
		jAXRSServerFactoryBean.setServiceBean(signataireResource);
		jAXRSServerFactoryBean.setServiceBean(affectationResource);
		jAXRSServerFactoryBean.setServiceBean(pieceReglementResource);
		jAXRSServerFactoryBean.setServiceBean(citerneResource);
		jAXRSServerFactoryBean.setServiceBean(fabriquantResource);
		jAXRSServerFactoryBean.setServiceBean(affectationProduitOperateurResource);
		jAXRSServerFactoryBean.setServiceBean(fileAttenteResource);
		jAXRSServerFactoryBean.setServiceBean(journeeActiviteResource);
		jAXRSServerFactoryBean.setServiceBean(ordreLivraisonResource);
		jAXRSServerFactoryBean.setServiceBean(destinationResource);
		jAXRSServerFactoryBean.setServiceBean(bonLivraisonResource);
        jAXRSServerFactoryBean.setServiceBean(motifResource);
        jAXRSServerFactoryBean.setServiceBean(emplissageResource);
		jAXRSServerFactoryBean.setServiceBean(circuitDerogationResource);
		jAXRSServerFactoryBean.setServiceBean(delegationResource);
        jAXRSServerFactoryBean.setServiceBean(ficheMarcheResource);
        jAXRSServerFactoryBean.setServiceBean(prixVenteClientResource);
		jAXRSServerFactoryBean.setServiceBean(dateResource);
		jAXRSServerFactoryBean.setServiceBean(typePieceReglementResource);

		jAXRSServerFactoryBean.setServiceBean(usersResource);
		jAXRSServerFactoryBean.setServiceBean(profilResource);
        jAXRSServerFactoryBean.setServiceBean(ficheMarcheResource);
        jAXRSServerFactoryBean.setServiceBean(prixVenteClientResource);
		jAXRSServerFactoryBean.setServiceBean(dateResource);

		jAXRSServerFactoryBean.setServiceBean(usersResource);
		jAXRSServerFactoryBean.setServiceBean(profilResource);
		jAXRSServerFactoryBean.setServiceBean(prixVenteConcessionnaireResource);
		jAXRSServerFactoryBean.setServiceBean(conditionCommercialeResource);
		return jAXRSServerFactoryBean.create();
	}

	@Bean
	public JAXRSServerFactoryBean getJAXRSServerFactoryBean() {
		JAXRSServerFactoryBean commonJaxRsFactoryBean = new JAXRSServerFactoryBean();

		extensionMappings.put("json", "application/json");
		inInterseptors.add(new LoggingInInterceptor());
		outInterseptors.add(new LoggingOutInterceptor());

		commonJaxRsFactoryBean.setBus(cxf());
		commonJaxRsFactoryBean.setExtensionMappings(this.extensionMappings);
		commonJaxRsFactoryBean.setProvider(new JacksonJsonProvider(new CustomJsonObjectMapper()));
		commonJaxRsFactoryBean.setProvider(new BackendExceptionMapper());
		// If you want CXF to log JSON input/output then uncomment below
		// jaxRsServerFactory.setFeatures(features --> core:logging);
		commonJaxRsFactoryBean.setInInterceptors(this.inInterseptors);
		commonJaxRsFactoryBean.setOutInterceptors(this.outInterseptors);

		return commonJaxRsFactoryBean;
	}

}
