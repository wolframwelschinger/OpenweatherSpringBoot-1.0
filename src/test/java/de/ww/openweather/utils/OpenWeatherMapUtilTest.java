package de.ww.openweather.utils;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import de.ww.openweather.utils.persistence.Ort;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenWeatherMapUtilTest {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	
//	@Autowired
//    private TestRestTemplate restTemplate;
	
	@Test
	public void test01GetWetter() {
		String url = "http://api.openweathermap.org/data/2.5/find?q=Berlin&units=metric&type=accurate&mode=json&APPID=a69fc6770115d5a82cd0d37e359ad4bf&lang=de";
//		String url = "http://api.openweathermap.org/data/2.5/find?q=13187,DE&units=metric&type=accurate&mode=json&APPID=a69fc6770115d5a82cd0d37e359ad4bf&lang=de";

		WetterDTO wetterDTO = OpenWeatherMapUtil.getWetter(url);
		boolean okay = true;
		if (wetterDTO == null) {
			okay = false;
			log.error("Fehler!");
		} else {
			System.out.println(wetterDTO.toString());
			if (!wetterDTO.getOrt().equals("Berlin")) {
				log.error("Ort ist nicht Berlin!");
				okay = false;
			}
			if (!wetterDTO.getLand().toLowerCase().equals("de")) {
				log.error("Land ist nicht DE!");
				okay = false;
			}			
		}
		assertEquals(true, okay);
	}

// >>>>>>>>>>>>>>>>>>>>> Achtung: Nachfolgende Tests laufen nur, wenn der Server laueft >>>>>>>>>>>>>>>>>>>>>>>>>>>


//	@Test
//	public void test02GetOrtById() {
//		RestTemplate restTemplate = new RestTemplate();
//		String resourceUrl = "http://127.0.0.1:9999/restservices/ort";
//
//		Ort ort = restTemplate
//				  .getForObject(resourceUrl + "/1", Ort.class);
//
//		System.out.println("Ort: " + ort.toString());
//
//		Assert.assertTrue(ort.getId() == 1l);
//		Assert.assertTrue(ort.getOrt().toLowerCase().contains("berlin"));
//
//	}
//
//	@Test
//	public void test03StoreOrt() {
//		String resourceUrl = "http://127.0.0.1:9999/restservices/ort";
//		ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//		RestTemplate restTemplate = new RestTemplate(requestFactory);
//
//		Ort ort = new Ort();
//		ort.setLand("DE");
//		ort.setOrt("BerlinX");
//		HttpEntity<Ort> httpEntityOrt = new HttpEntity<Ort>(ort);
//
//		Ort postedOrt = restTemplate.postForObject(resourceUrl, httpEntityOrt, Ort.class);
//		Assert.assertTrue(postedOrt.getOrt().equals("BerlinX"));
//	}
//
//	@Test
//	public void test04DeleteOrt() {
//
//		RestTemplate restTemplate = new RestTemplate();
//		String findUrl = "http://127.0.0.1:9999/restservices/ortByName";
//
//		Ort ort = restTemplate
//				  .getForObject(findUrl + "/BerlinX", Ort.class);
//
//		log.debug("Ort gefunden: " + ort.toString());
//
//		String deleteUrl = "http://127.0.0.1:9999/restservices/ort";
//		ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//		RestTemplate deleteTemplate = new RestTemplate(requestFactory);
//
//		long id = ort.getId();
//		String entityUrl = deleteUrl + "/" + id;
//		deleteTemplate.delete(entityUrl);
//
//		log.debug("Ort geloesch: " + ort.toString());
//
//		Ort ortDeleted = restTemplate
//				  .getForObject("http://127.0.0.1:9999/restservices/ort" + "/" + id, Ort.class);
//
//		Assert.assertTrue(ortDeleted == null);
//	}
	
	
/*	
Fehler:
	
	2017-11-19 18:07:10,245 ERROR TestContextManager:234 - Caught exception while allowing TestExecutionListener [org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener@561b6512] to prepare test instance [de.ww.openweather.utils.OpenWeatherMapUtilTest@3302035b]
			org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'de.ww.openweather.utils.OpenWeatherMapUtilTest': Unsatisfied dependency expressed through field 'restTemplate'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.boot.test.web.client.TestRestTemplate' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
				at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:588) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:88) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:366) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1264) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireBeanProperties(AbstractAutowireCapableBeanFactory.java:386) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:118) ~[spring-test-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:83) ~[spring-test-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener.prepareTestInstance(SpringBootDependencyInjectionTestExecutionListener.java:44) ~[spring-boot-test-autoconfigure-1.5.1.RELEASE.jar:1.5.1.RELEASE]
				at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:230) [spring-test-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.createTest(SpringJUnit4ClassRunner.java:228) [spring-test-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.test.context.junit4.SpringJUnit4ClassRunner$1.runReflectiveCall(SpringJUnit4ClassRunner.java:287) [spring-test-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12) [junit-4.12.jar:4.12]
				at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.methodBlock(SpringJUnit4ClassRunner.java:289) [spring-test-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:247) [spring-test-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:94) [spring-test-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290) [junit-4.12.jar:4.12]
				at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71) [junit-4.12.jar:4.12]
				at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288) [junit-4.12.jar:4.12]
				at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58) [junit-4.12.jar:4.12]
				at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268) [junit-4.12.jar:4.12]
				at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61) [spring-test-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70) [spring-test-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.junit.runners.ParentRunner.run(ParentRunner.java:363) [junit-4.12.jar:4.12]
				at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:191) [spring-test-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86) [.cp/:?]
				at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38) [.cp/:?]
				at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:539) [.cp/:?]
				at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:761) [.cp/:?]
				at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:461) [.cp/:?]
				at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:207) [.cp/:?]
			Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.boot.test.web.client.TestRestTemplate' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
				at org.springframework.beans.factory.support.DefaultListableBeanFactory.raiseNoMatchingBeanFound(DefaultListableBeanFactory.java:1486) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1104) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1066) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:585) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
				... 29 more
			2017-11-19 18:07:10,252  INFO GenericWebApplicationContext:987 - Closing org.springframework.web.context.support.GenericWebApplicationContext@1224144a: startup date [Sun Nov 19 18:07:02 CET 2017]; root of context hierarchy
*/
	
	
//	@Test
//	public void testGetOrte() {
//		
//		String page = "{\"index\":1,\"size\":1}";
//		
//		@SuppressWarnings("unchecked")
//		Page<Ort> resultPage = this.restTemplate.getForObject(
//				"/restservices/anwendung/list?page={page}", 
//				RestPageImpl.class, page);
//		
//		log.info("Anzahl der Orte: " + resultPage.getContent().size());
//		assertEquals(true, resultPage.getContent().size() > 0);
//	}
	
//	@Test
//	public void testGetOrtById(){
//		Ort result = this.restTemplate.getForObject(
//				"/restservices/ort/1", 
//				Ort.class);
//		log.debug(result);
//		Assert.assertTrue(result.getId() == 1L);
//	}	
	
}
