package org.integrallis.drools.examples;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.internal.io.ResourceFactory;

/**
 * This is a sample class to launch a rule.
 */
public class AccumulateExample {

	private static final KieServices kieServices = KieServices.Factory.get();
	private static final String RULES_CUSTOMER_RULES_DRL = "rules/accumulate.drl";
	
	public static final void main(String[] args) {
	    KieSession ksession = null;

	    try {
	        // load up the knowledge base
//	        KieServices ks = KieServices.Factory.get();
//		    KieContainer kContainer = ks.getKieClasspathContainer();
//		    ksession = kContainer.newKieSession("ksession-rules");
	    	
	         KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
	         kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_CUSTOMER_RULES_DRL));
	         KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
	         kb.buildAll();
	         KieModule kieModule = kb.getKieModule();
	         KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
	         
	         ksession = kieContainer.newKieSession();
	         System.out.println("ksession :: "+ksession);
	        
			// go !
			Person smokey = new Person("Smokey");
			Ticket ticket1 = new Ticket(smokey, 1500.0, "SPEEDING1", false, "Self");
			Ticket ticket2 = new Ticket(smokey, 1500.0, "SPEEDING1", false, "Self");
			Ticket ticket3 = new Ticket(smokey, 1900.0, "SPEEDING1", false, "Self");
			Ticket ticket4 = new Ticket(smokey, 600.0, "SPEEDING1", false, "Self");
			Ticket ticket5 = new Ticket(smokey, 1500.0, "SPEEDING2", false, "Self");
			Ticket ticket6 = new Ticket(smokey, 1500.0, "SPEEDING2", false, "Self");
			Ticket ticket7 = new Ticket(smokey, 1900.0, "SPEEDING2", false, "Self");
			Ticket ticket8 = new Ticket(smokey, 600.0, "SPEEDING2", false, "Self");
			Ticket ticket11 = new Ticket(smokey, 1500.0, "SPEEDING", false, "Self");
			Ticket ticket21 = new Ticket(smokey, 1500.0, "SPEEDING", false, "Self");
			Ticket ticket31 = new Ticket(smokey, 1900.0, "SPEEDING", false, "Self");
			Ticket ticket41 = new Ticket(smokey, 600.0, "SPEEDING", false, "Self");
			
			ksession.insert(smokey);
			ksession.insert(ticket1);
			ksession.insert(ticket2);
			ksession.insert(ticket3);
			ksession.insert(ticket4);
			ksession.insert(ticket5);
			ksession.insert(ticket6);
			ksession.insert(ticket7);
			ksession.insert(ticket8);
			ksession.insert(ticket11);
			ksession.insert(ticket21);
			ksession.insert(ticket31);
			ksession.insert(ticket41);
			
			ksession.fireAllRules();
			
			System.out.println("===== QUERYING =====");
			QueryResults results = ksession.getQueryResults( "GetWarrants" );
//			QueryResults results = ksession.getQueryResults( "ArrestWarrantIssued" );
			
			for ( QueryResultsRow row : results ) {
			    ArrestWarrant warrant = (ArrestWarrant) row.get( "warrant" );
			    System.out.println(warrant);
			}	
		} catch (Throwable t) {
			t.printStackTrace();
        } finally {
          	ksession.dispose();
        }
	}
}