package mx.golondrina.ferreteria.DP03_U1_A2_ERVV;

import javax.swing.SwingUtilities;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FerreteriaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(FerreteriaApplication.class)
				.headless(false)
				.run(args);

		SwingUtilities.invokeLater(() -> {

		});
	}
}