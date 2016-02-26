package fr.youtix;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	private static final Logger log = LogManager.getLogger(App.class);
	
	private App() {}

	public static void main(String[] args) {

		String[] springConfig  = 
			{	
				"spring/batch/config/context.xml",
				"spring/batch/jobs/context-service.xml",
				"spring/batch/jobs/context-listener.xml",
				"spring/batch/jobs/context-reader.xml",
				"spring/batch/jobs/context-writer.xml",
				"spring/batch/jobs/context-job.xml"
			};
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(springConfig)) {
		
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("recordJob");
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			log.info("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			log.error(e);
		}

		log.info("Done");

	}
}
