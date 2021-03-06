package com.ayouris.tawassol.rest.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ayouris.tawassol.resource.BanqueResource;
import com.ayouris.tawassol.resource.CiterneResource;
import com.ayouris.tawassol.resource.ControleCamionResource;
import com.ayouris.tawassol.resource.EnumResource;
import com.ayouris.tawassol.resource.FabriquantResource;
import com.ayouris.tawassol.resource.FournisseurResource;
import com.ayouris.tawassol.resource.GroupResource;
import com.ayouris.tawassol.resource.MarqueResource;
import com.ayouris.tawassol.resource.PrestationResource;
import com.ayouris.tawassol.resource.TransporteurResource;
import com.ayouris.tawassol.resource.TypeFileAttenteResource;

@Configuration
public class RestSecurityConfig {

//	@Autowired
//	private UserResourceImpl userResource;
	
	@Autowired
	private BanqueResource banqueResource;
	@Autowired
	private GroupResource groupResource;
	@Autowired
	private MarqueResource marqueResource;
	@Autowired
	private TransporteurResource transporteurResource;
	@Autowired
	private FournisseurResource fournisseurResource ;
	@Autowired
	private PrestationResource prestationResource ;
	@Autowired
	private TypeFileAttenteResource typeFileAttenteResource ;
	@Autowired
	private ControleCamionResource controleCamionResource ;
	@Autowired
	private CiterneResource citerneResource;
	@Autowired
	private FabriquantResource fabriquantResource;
	

	@Autowired
	private EnumResource enumResource;

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
		
		jAXRSServerFactoryBean.setServiceBean(enumResource);
		jAXRSServerFactoryBean.setServiceBean(banqueResource);
		jAXRSServerFactoryBean.setServiceBean(groupResource);
		jAXRSServerFactoryBean.setServiceBean(marqueResource);
		jAXRSServerFactoryBean.setServiceBean(transporteurResource);
		jAXRSServerFactoryBean.setServiceBean(fournisseurResource);
		jAXRSServerFactoryBean.setServiceBean(typeFileAttenteResource);
		jAXRSServerFactoryBean.setServiceBean(prestationResource);
		jAXRSServerFactoryBean.setServiceBean(controleCamionResource);
		jAXRSServerFactoryBean.setServiceBean(citerneResource);
		jAXRSServerFactoryBean.setServiceBean(fabriquantResource);
				
		
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
