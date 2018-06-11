/*package tests;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

public class OrderMapper extends JUnitStories{
	
	public OrderMapper(){
		super();
		this.configuredEmbedder().candidateSteps().add(new OrderSteps());
	}
	
	
	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration()
		.useStoryLoader(new LoadFromClasspath(this.getClass())).useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats()
		.withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML));
	}
	
	@Override
	public List<CandidateSteps> candidateSteps() {
	return new InstanceStepsFactory(configuration(), new OrderSteps()).createCandidateSteps();
	}

	@Override
	protected List<String> storyPaths() {
		// TODO Auto-generated method stub
		return Arrays.asList("Order.story");
	}

}
*/
