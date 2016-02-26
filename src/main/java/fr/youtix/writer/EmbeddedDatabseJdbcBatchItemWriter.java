package fr.youtix.writer;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import fr.youtix.model.Record;

public class EmbeddedDatabseJdbcBatchItemWriter extends JdbcBatchItemWriter<Record>{
	
	private static final Logger log = LogManager.getLogger(EmbeddedDatabseJdbcBatchItemWriter.class);

	@Override
	public void write(List<? extends Record> listOfReport) throws Exception {
		super.write(listOfReport);
		log.info(listOfReport.size() + " items written !");
	}

}
