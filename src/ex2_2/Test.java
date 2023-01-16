package ex2_2;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class Tests {

	public static org.junit.platform.commons.logging.Logger logger = LoggerFactory.getLogger(Tests.class);

	@Test
	public void partialTest(){

		CustomExecutor customExecutor = new CustomExecutor();
		Task task = Task.createTask(()->{
			int sum = 0;
			for (int i = 1; i <= 10; i++) {
				sum += i;
			}
			return sum;
		}, TaskType.COMPUTATIONAL);
		Future sumTask = customExecutor.submit(task);
		final int sum;
		try {
			sum = (int) sumTask.get(1, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		logger.info(()-> "Sum of 1 through 10 = " + sum);
		Callable<Double> callable1 = ()-> {
			return 1000 * Math.pow(1.55, 2);
		};
		Callable<String> callable2 = ()-> {
			StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			return sb.reverse().toString();
		};

		Future<Double> priceTask = customExecutor.submit(()-> {
			return 1000 * Math.pow(1.02, 5);
		}, TaskType.COMPUTATIONAL);
		Future<String> reverseTask = customExecutor.submit(callable2, TaskType.IO);
		final Double totalPrice;
		final String reversed;
		final String reversed2;
		final String reversed3;
		
		Callable<String> callable3 = ()-> {
			StringBuilder sb = new StringBuilder("zzz");
			return sb.reverse().toString();
			
		};
		Callable<String> callable4 = ()-> {
			StringBuilder sb = new StringBuilder("11111111");
			return sb.reverse().toString();
			
		};
		Future<String> reverseTask2 = customExecutor.submit(callable3, TaskType.IO);
		Future<String> reverseTask3 = customExecutor.submit(callable4, TaskType.IO);

		try {
			totalPrice = priceTask.get();
			reversed = reverseTask.get();
			reversed2 = reverseTask2.get();
			reversed3 = reverseTask3.get();

			
		} catch (Exception e) {
			throw new RuntimeException();
		}
		logger.info(()-> "R is = " + reversed);
		logger.info(()->String.valueOf("total of" + totalPrice));
		logger.info(()-> "current of array list = " +customExecutor.getCurrentMax());
		logger.info(()->String.valueOf("total is = " + reversed3));
		logger.info(()->String.valueOf("total is = " + reversed3));
		
		customExecutor.dwon();
	}

}