package br.com.moutsti.ordermanager;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class ArchitectureTest {

	private JavaClasses importedClasses;

	@BeforeEach
	public void init() {
		importedClasses = new ClassFileImporter().withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
				.importPackages("br.com.moutsti.ordermanager");
	}

	@Test
	void repositories_should_be_package_private() {
		classes().that().haveSimpleNameEndingWith("Repository").should().bePackagePrivate()
				.because("Repositories should be package private.").check(importedClasses);
	}

	@Test
	void command_impl_classes_should_be_annotated_with_transaction_service() {
		classes().that().haveSimpleNameEndingWith("Command").and().resideOutsideOfPackage("..core..").and()
				.resideOutsideOfPackage("..integration").should().beAnnotatedWith(Transactional.class).andShould()
				.beAnnotatedWith(Service.class)
				.because("Command classes should be annotated with service and transactional.").check(importedClasses);
	}

	@Test
	void query_impl_classes_should_be_annotated_with_transaction_service() {
		classes().that().haveSimpleNameEndingWith("Query").and().resideOutsideOfPackage("..core..").should()
				.beAnnotatedWith(Transactional.class).andShould().beAnnotatedWith(Service.class)
				.because("Query classes should be annotated with service and transactional.").check(importedClasses);
	}

}
