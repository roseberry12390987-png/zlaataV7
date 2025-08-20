package stepDef;



import java.util.concurrent.TimeoutException;

import context.TestContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CouponPage;
import pages.LoginPage;
import pages.NegativeSignupPages;

public class CouponFunctionality {

	TestContext testContext;
	CouponPage coupon;
	NegativeSignupPages negsignup;
	LoginPage login;


	public  CouponFunctionality(TestContext context) {
		testContext = context;
		coupon= testContext.getPageObjectManager().getCouponPage();
		negsignup = testContext.getPageObjectManager().getNegativeSignupPages();
		login = testContext.getPageObjectManager().getLoginPage();

	}

	//14		

	@Given("User is on the checkout page with items in the cart")
	public void user_is_on_the_checkout_page_with_items_in_the_cart() throws TimeoutException 
	{

		negsignup.signUp();

		coupon.checkout();
		//		coupon.FirstBuy();

	}

	@When("User enters an invalid coupon code")
	public void user_enters_an_invalid_coupon_code() {

		coupon.invalidCouponCode();

	}

	@And("User clicks on the Apply button in the coupon popup")
	public void user_clicks_on_the_apply_button_in_the_coupon_popup() {
		coupon.CouponPopupApplyButton();
	}

	@Then("An error message should be displayed indicating the coupon code is invalid")
	public void an_error_message_should_be_displayed_indicating_the_coupon_code_is_invalid() {
		coupon.invalidcouponCodeValidationMessage("Invalid Coupon Code, Please enter Valid Code");

	}

	//18
	@And("User applies a valid coupon code")
	public void user_applies_a_valid_coupon_code() {

		coupon.appliedValidCouponCode();
	}


	@Then("The same coupon discount amount should be displayed consistently on Checkout, Address, and Payment pages")
	public void the_same_coupon_discount_amount_should_be_displayed_consistently_on_checkout_address_and_payment_pages() {

		coupon.sameCouponAmountDisplayingInAllThreePages();


	}



	//17

	@When("User refreshes the page")
	public void user_refreshes_the_page() {

		coupon.refreshThePage();


	}


	@Then("The applied coupon discount should disappear or be recalculated as per eligibility")
	public void the_applied_coupon_discount_should_disappear_or_be_recalculated_as_per_eligibility()
	{
		coupon.checkTheCouponAmountAfterRefresh();
	}



	


	//16


	@Then("Coupon discount should be applied successfully")
	public void coupon_discount_should_be_applied_successfully() {

		coupon.CouponAppliedValidationMessage();


	}

	@When("User clicks on the Remove button")
	public void user_clicks_on_the_remove_button() {

		coupon.removeCouponFunctionality("Coupon removed successfully!");

	}
	@Then("Coupon should be removed and discount reversed")
	public void coupon_should_be_removed_and_discount_reversed() {

		coupon.CouponRemovedOrNot();
	}


	//15


	@Given("User is on the checkout page with items in the cart before login")
	public void user_is_on_the_checkout_page_with_items_in_the_cart_before_login() {

		coupon.addPropdcutToCart();
	}




	@Given("User logs in using the coupon popup Apply button")
	public void user_logs_in_using_the_coupon_popup_apply_button() {


		coupon.loginusingApplyButtonOnCouponPopup();


	}

	//13

	@When("User click on view coupon button")
	public void user_click_on_view_coupon_button() {
		coupon.clickOnViewCouponButton();

	}

	@Then("A message should be displayed indicating the need to add more items to unlock additional coupons")
	public void a_message_should_be_displayed_indicating_the_need_to_add_more_items_to_unlock_additional_coupons() {
		coupon.unlockMoreCoupons();

	}


	//12
	@Then("Only the eligible coupons should be displayed")
	public void only_the_eligible_coupons_should_be_displayed() {
		coupon.availableCoupon();
	}
	//11



	@Given("User enters a valid Special Coupon code with a fixed amount discount")
	public void user_enters_a_valid_special_coupon_code_with_a_fixed_amount_discount() {

		coupon.specialCouponCodeWithfixedAmount();
	}



	@Then("Special Coupon with fixed amount   should be applied successfully")
	public void special_coupon_with_fixed_amount_should_be_applied_successfully() {

		coupon.fixedAmountAppliedSuccessfullForSpecialCoupon();

	}
	//10

	@Given("User enters a valid Special Coupon code with a percentage discount")
	public void user_enters_a_valid_special_coupon_code_with_a_percentage_discount() {

		coupon.specialCouponCodeWithPercentageAmount();

	}


	@Then("Special Coupon with percentage discount should be applied successfully")
	public void special_coupon_with_percentage_discount_should_be_applied_successfully() {

		coupon.PercentageAmountAppliedSuccessfullForSpecialCoupon();
	}


//09


		@Given("User enters a valid Normal Coupon code with a fixed amount discount")
	public void user_enters_a_valid_normal_coupon_code_with_a_fixed_amount_discount() {
			
			coupon.NormalCouponCodeWithfixedAmount();
}

		@Then("Normal Coupon with fixed amount   should be applied successfully")
	public void normal_coupon_with_fixed_amount_should_be_applied_successfully() {
			
			coupon.fixedAmountAppliedSuccessfullForNormalCoupon();

	}













}

