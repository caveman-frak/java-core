package uk.co.bluegecko.core.server.test;


import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import uk.co.bluegecko.core.ApplicationConfig;
import uk.co.bluegecko.core.server.test.interceptor.AcceptHeaderHttpRequestInterceptor;


@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest( classes = ApplicationConfig.class, webEnvironment = WebEnvironment.RANDOM_PORT )
public abstract class AbstractWebTest implements ServerPorts
{

	@Autowired
	private WebApplicationContext context;

	@LocalServerPort
	private int httpPort;
	@Value( "${local.management.port:-1}" )
	private int jmxPort;

	protected WebApplicationContext getContext()
	{
		return context;
	}

	@Override
	public int getHttpPort()
	{
		return httpPort;
	}

	@Override
	public int getJmxPort()
	{
		return jmxPort;
	}

	protected List< HttpMessageConverter< ? > > createJaxbMessageConverter()
	{
		// set-up message converters to support JAXB annotations
		final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector( TypeFactory.defaultInstance() );
		final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();
		final AnnotationIntrospectorPair introspectorPair = new AnnotationIntrospectorPair( jaxbIntrospector,
				jacksonIntrospector );

		final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.featuresToEnable( MapperFeature.DEFAULT_VIEW_INCLUSION )
				.annotationIntrospector( introspectorPair );

		return Arrays.asList( new MappingJackson2HttpMessageConverter( builder.build() ),
				new MappingJackson2XmlHttpMessageConverter( builder.createXmlMapper( true )
						.build() ) );
	}

	protected List< ClientHttpRequestInterceptor > createMediaTypeInterceptor( final MediaType mediaType )
	{
		return Arrays.asList( new AcceptHeaderHttpRequestInterceptor( mediaType ) );
	}

}
