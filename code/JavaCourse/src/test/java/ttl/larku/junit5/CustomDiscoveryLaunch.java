package ttl.larku.junit5;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.launcher.TagFilter.includeTags;

public class CustomDiscoveryLaunch {

    public static void main(String[] args) {
        CustomDiscoveryLaunch cdl = new CustomDiscoveryLaunch();
        cdl.discoverAndLaunch();
    }

    public void discoverAndLaunch() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        //selectPackage("ttl.larku.junit5"),
                        selectClass(JUnit5Tags.class)
                ).filters(
                        //excludeTags("integration")
//                        includeTags("fast | integration")
                        includeTags("fast")
                ).build();

        Launcher launcher = LauncherFactory.create();

        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();
        System.out.println("Containers run: " + summary.getContainersStartedCount() +
                ", Containers passed: " + summary.getContainersSucceededCount() +
                ", Containers aborted: " + summary.getContainersAbortedCount() +
                ", Containers failed: " + summary.getContainersFailedCount());

        System.out.println("Tests run: " + summary.getTestsStartedCount() +
                ", Tests passed: " + summary.getTestsSucceededCount() +
                ", Tests aborted: " + summary.getTestsAbortedCount() +
                ", Tests failed: " + summary.getTestsFailedCount());

        System.out.println("All Done");
    }

}
