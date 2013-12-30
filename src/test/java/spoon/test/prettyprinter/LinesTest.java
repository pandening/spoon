package spoon.test.prettyprinter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import spoon.Spoon;
import spoon.compiler.SpoonResourceHelper;
import spoon.reflect.Factory;
import spoon.reflect.declaration.CtSimpleType;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;

public class LinesTest {

	Factory factory;

	@Before
	public void setup() throws Exception {
		factory = Spoon.createFactory();
		Spoon.createCompiler(
				factory,
				SpoonResourceHelper
						.resources("./src/test/java/spoon/test/prettyprinter/Validation.java"))
				.build();
		factory.getEnvironment().setPreserveLineNumbers(true);
		factory.getEnvironment().setAutoImports(false);
	}

	@Test
	public void testPrettyPrinterWithLines() throws Exception {

		for (CtSimpleType<?> t : factory.Type().getAll()) {
			if (t.isTopLevel()) {
				// System.out.println("calculate " + t.getSimpleName());
				DefaultJavaPrettyPrinter pp = new DefaultJavaPrettyPrinter(
						factory.getEnvironment());
				pp.calculate(t.getPosition().getCompilationUnit(), t
						.getPosition().getCompilationUnit().getDeclaredTypes());
				// System.out.println(pp.getResult().toString());
			}
		}
		assertEquals(0, factory.getEnvironment().getWarningCount());
		assertEquals(0, factory.getEnvironment().getErrorCount());

	}

}