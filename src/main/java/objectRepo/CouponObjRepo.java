package objectRepo;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public abstract class CouponObjRepo extends BasePage {
	
	@FindBy(xpath ="//button[@class='checkout_details_sub_heading viewCouponBtn']")
	protected WebElement viewCouponButton;
	
	@FindBy(xpath = "//input[@class='coupon_input Cls_coupon_input ']" )
	protected WebElement couponCodeTextBox;
	
	@FindBy(xpath = "//div[@class='popup_containers_content']//button[@class='coupon_input_apply_btn ']")
	protected WebElement couponPopupApplyButton;
	
	@FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
	protected WebElement invalidCouponCodeValidationmMessage;
	
	@FindBy(xpath = "//p[@class='coupon_apply_msg active']")
	protected WebElement appliedCouponAmountOnCouponPopup;
	
	@FindBy(xpath = "//p[@class='acc_details_status']")
	protected WebElement appliedCouponAmountOnCouponsSection;
	
	@FindBy(xpath = "//div[@class='price_details_pair Cls_cart_coupon_discount ']")
	protected WebElement  appliedCouponAmountOnCheckoutPage;
	
	@FindBy(xpath = "//div[@class='price_details_pair Cls_cart_coupon_discount ']")
	protected WebElement  appliedCouponAmountOnAddressPage;
	
	@FindBy(xpath = "//div[@class='price_details_pair Cls_cart_coupon_discount ']")
	protected WebElement  appliedCouponAmountOnPaymentPage;
	
	@FindBy(xpath = "//button[@class='place_order_btn Cls_place_order btn___2 ']")
	protected WebElement continueButtonOnCheckoutPage;
	
	@FindBy(xpath = "//button[@class='place_order_btn Cls_place_order btn___2 enabled']")
	protected WebElement  continueButtonOnCheckoutPageaddress;
	
	@FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
	protected WebElement minimumOrderValidationMessage;
	
   @FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
   protected WebElement couponAppliedSuccessfullyValidationMessage;
   
   @FindBy(xpath = "//div[@class='coupon_input_wrap']//button[@class='coupon_input_apply_btn coupon_input_remove_btn']")
   protected WebElement couponPopupRemoveButton;
   
   @FindBy(xpath = "//div[@class='login_process_wrap']")
	protected WebElement loginPopup;
   
	@FindBy(xpath = "//button[@class='signup_box_btn']")
	protected WebElement signupButton;

   
   @FindBy(id  = "signupContainer")
   protected WebElement signupPopup;
    
  /// 🔹 Unlock More Coupons section (non eligible coupons)
   @FindBy(xpath = "//div[@class='coupon_list_wrap non_eligible_coupons']")
   protected WebElement unlockMoreCoupon;

   @FindBy(xpath = "//div[@class='coupon_list_wrap non_eligible_coupons']//div[@class='coupon_heading']")
   protected List<WebElement> unlockCouponCode;

   @FindBy(xpath = "//div[@class='coupon_list_wrap non_eligible_coupons']//div[@class='coupon_details']")
   protected List<WebElement> unlockCouponName;


   // 🔹 Available Coupons section (eligible coupons)
   @FindBy(xpath = "//div[@class='coupon_list_wrap eligible_coupons']")
   protected WebElement availableCoupon;

   @FindBy(xpath = "//div[@class='coupon_list_wrap eligible_coupons']//div[@class='coupon_heading']")
   protected List<WebElement> availableCouponCode;

   @FindBy(xpath = "//div[@class='coupon_list_wrap eligible_coupons']//div[@class='coupon_details']")
   protected List<WebElement> availableCouponName;
   
   @FindBy(xpath = "(//div[@class='popup_containers_cls_btn'])[4]")
   protected    WebElement couponPopupcloseButton;
   
	@FindBy(xpath ="//button[@class='cp_quantity_increase_btn  Cls_cp_quantity_increase_btn ']")
	protected WebElement increaseTheProductQunatity;

   
}
