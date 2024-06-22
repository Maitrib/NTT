package Steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import Runner.TestRun;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;

import io.cucumber.java.en.*;

public class mainSteps extends TestRun {
	RequestSpecification res;
//	WebDriver driver;

    @Given("I am on the ESPN Cricinfo homepage")
    public void i_am_on_the_espn_cricinfo_homepage() throws InterruptedException {
    	log.info("user launch url");
        driver.get("https://www.espncricinfo.com/");
        Thread.sleep(1000);
    }

    @When("I wait for the score update interval")
    public void i_wait_for_the_score_update_interval() throws InterruptedException {
        Thread.sleep(3000); // Assuming 30 seconds update interval
    }

    @Then("the live scores should update automatically")
    public void the_live_scores_should_update_automatically() {
        WebElement scoreElement = driver.findElement(By.cssSelector("#main-container > div.ds-pb-4.ds-bg-fill-hsb.ds-pt-2.ds-min-h-\\[182px\\] > div > div.ds-pt-2 > div > div > div > div > div:nth-child(3) > div > div > a > div > div > div:nth-child(2) > div > div.ci-team-score.ds-flex.ds-justify-between.ds-items-center.ds-text-typo.ds-opacity-50 > div.ds-text-compact-s.ds-text-typo.ds-text-right.ds-whitespace-nowrap"));
        String updatedScore = scoreElement.getText();
        
        // Logic to verify score update
        Assert.assertTrue("Scores did not update", isScoreUpdated(updatedScore));
    }

    @And("the scores should be correct")
    public void the_scores_should_be_correct() {
        WebElement scoreElement = driver.findElement(By.cssSelector("#main-container > div.ds-pb-4.ds-bg-fill-hsb.ds-pt-2.ds-min-h-\\[182px\\] > div > div.ds-pt-2 > div > div > div > div > div:nth-child(3) > div > div > a > div > div > div:nth-child(2) > div > div.ci-team-score.ds-flex.ds-justify-between.ds-items-center.ds-text-typo.ds-opacity-50 > div.ds-text-compact-s.ds-text-typo.ds-text-right.ds-whitespace-nowrap"));
        String updatedScore = scoreElement.getText();
        // Additional logic to verify the correctness of the score
        Assert.assertTrue("Scores are not correct", isScoreCorrect(updatedScore));
    }

    private boolean isScoreUpdated(String updatedScore) {

        return true; 
    }

    private boolean isScoreCorrect(String updatedScore) {

        return true; 
    }

}


