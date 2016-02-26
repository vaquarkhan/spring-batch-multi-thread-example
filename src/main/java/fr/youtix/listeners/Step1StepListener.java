package fr.youtix.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import fr.youtix.writer.EmbeddedDatabseJdbcBatchItemWriter;

public class Step1StepListener implements StepExecutionListener {
	
	private static final Logger log = LogManager.getLogger(EmbeddedDatabseJdbcBatchItemWriter.class);

	@Override
	public void beforeStep(StepExecution arg0) {
		log.info("Début de l'étape !");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		log.info(stepExecution.getSummary());
		log.info("Fin de l'étape !");
		return null;
	}

}
