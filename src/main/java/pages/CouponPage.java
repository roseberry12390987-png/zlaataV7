package pages;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import manager.FileReaderManager;
import objectRepo.CouponObjRepo;
import stepDef.Hooks;
import utils.Common;






public final class CouponPage  extends CouponObjRepo{
	private WebDriverWait wait;



	
	public CouponPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void checkout() {
		
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		

		Common.waitForElement(2);
		pdp.buyNow(Hooks.getScenario());

	}
//	public void FirstBuy() throws TimeoutException {
//		// Step 1: Open coupon popup
//		wait.until(ExpectedConditions.elementToBeClickable(viewCouponButton)).click();
//		// Step 2: If coupon locked, keep increasing qty until unlocked
//		if (isCouponLocked()) {
//			System.out.println(":lock: Coupon locked — unlocking...");
//			// Close popup
//			wait.until(ExpectedConditions.elementToBeClickable(couponPopupcloseButton)).click();
//			// Loop until unlocked
//			while (true) {
//				increaseProductQuantity();
//				System.out.println(":shopping_trolley: Increased qty — rechecking...");
//				wait.until(ExpectedConditions.elementToBeClickable(viewCouponButton)).click();
//				if (!isCouponLocked()) {
//					System.out.println(":unlock: Coupon unlocked!");
//					break;
//				} else {
//					wait.until(ExpectedConditions.elementToBeClickable(couponPopupcloseButton)).click();
//					ProductDetailsPage pdp = new ProductDetailsPage(driver);
//					pdp.buyNow(Hooks.getScenario());
//				}
//			}
//		}
//		// Step 3: Apply coupon
//		wait.until(ExpectedConditions.visibilityOf(couponCodeTextBox))
//		.sendKeys(FileReaderManager.getInstance().getJsonReader().getValueFromJson("FirstBuy"));
//		click(couponPopupApplyButton);
//	}
//	/**
//	 * Check if coupon is locked
//	 */
//	private boolean isCouponLocked() throws TimeoutException {
//		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
//		WebElement lockedElement = shortWait.until(
//				ExpectedConditions.presenceOfElementLocated(
//						By.xpath("//div[@class='coupon_list_wrap non_eligible_coupons']"))
//				);
//		return lockedElement.isDisplayed();
//	}
//	/**
//	 * Increase product quantity
//	 */
//	private void increaseProductQuantity() {
//		wait.until(ExpectedConditions.elementToBeClickable(increaseTheProductQunatity)).click();
//	}
//	/**
//	 * Verify success message
//	 */
//	public void verifyAppliedMessage(String expectedText) {
//		By successMessageLocator = By.xpath("//div[@class='snackbar-container  snackbar-pos top-right']");
//		// Wait until the snackbar appears (freshly locating it)
//		String actualText = wait.until(ExpectedConditions
//				.visibilityOfElementLocated(successMessageLocator))
//				.getText().trim();
//		Assert.assertTrue(":x: Expected success text not found. Actual: " + actualText,
//				actualText.toLowerCase().contains(expectedText.toLowerCase()));
//		System.out.println(":white_tick: Applied message appeared: " + actualText);
//	}

	private void newAddress() {
		AddressPage address = new AddressPage(driver);
		address.newAddressData();

	}
	
	private String getCleanAmount(WebElement element) {
	    return element.getText().replaceAll("[^0-9]", "").trim();
	}

	private String getCleanAmountWithWait(WebElement element, int seconds) {
	    WebElement el = new WebDriverWait(driver, Duration.ofSeconds(seconds))
	            .until(ExpectedConditions.visibilityOf(element));
	    return el.getText().replaceAll("[^0-9]", "").trim();
	}

	

	public void invalidCouponCode() {
		
	 click(viewCouponButton);	
     type(couponCodeTextBox, FileReaderManager.getInstance().getJsonReader().getValueFromJson("InvalidCouponCode"));
     }
	
	public void CouponPopupApplyButton() {
		click(couponPopupApplyButton);

	}

	public void invalidcouponCodeValidationMessage(String expectedText ) {
		
		By inValidCoupon = By.xpath("//div[@class='snackbar-container  snackbar-pos top-right']");
	    // Wait until the snackbar appears (freshly locating it)
	    String actualText = wait.until(ExpectedConditions
	            .visibilityOfElementLocated(inValidCoupon))
	            .getText().trim();
	    Assert.assertTrue(":x: Expected success text not found. Actual: " + actualText,
	            actualText.toLowerCase().contains(expectedText.toLowerCase()));
	    System.out.println( "\u001B[0m" + "Error message appeared: " + actualText  + "\u001B[0m");
	}

	//18   
   public void appliedValidCouponCode() 
   {
		click(viewCouponButton);	
	     type(couponCodeTextBox, FileReaderManager.getInstance().getJsonReader().getValueFromJson("FirstBuyCouponCode"));
	     click(couponPopupApplyButton);
	     
	    
	     

	     }
   public void sameCouponAmountDisplayingInAllThreePages() {
	   
	   Common.waitForElement(2);

	// Coupon Popup
	String popupAmount = getCleanAmount(appliedCouponAmountOnCouponsSection);
	System.out.println("Coupon Amount on Coupon Popup   : " + popupAmount);

	// Refresh the page
	driver.navigate().refresh();

	// Checkout Page
	String checkoutAmount = getCleanAmount(appliedCouponAmountOnCheckoutPage);
	System.out.println("Coupon Amount on Checkout Page  : " + checkoutAmount);

	// Continue to Address Page
	click(continueButtonOnCheckoutPage);
	newAddress();
	String addressAmount = getCleanAmount(appliedCouponAmountOnAddressPage);
	System.out.println("Coupon Amount on Address Page   : " + addressAmount);

	// Continue to Payment Page
	click(continueButtonOnCheckoutPageaddress);
	String paymentAmount = getCleanAmountWithWait(appliedCouponAmountOnPaymentPage, 10); // wait up to 10s
	System.out.println("Coupon Amount on Payment Page   : " + paymentAmount);

	// Validations
	Assert.assertEquals("Mismatch between Coupon Popup and Checkout Page", popupAmount, checkoutAmount);
	Assert.assertEquals("Mismatch between Address Page and Payment Page", addressAmount, paymentAmount);

	System.out.println("✅ Coupon amount matches on Coupon Popup & Checkout Page, and Address & Payment pages.");

   }
	
	//17

	  public void refreshThePage() {
		

		  Common.waitForElement(5);
	
		   String popupAmount = getCleanAmount(appliedCouponAmountOnCouponsSection);
			System.out.println("Coupon Amount on Coupon Section before Refresh the page   : " + popupAmount);
			
			String checkoutAmount = getCleanAmount(appliedCouponAmountOnCheckoutPage);
			System.out.println("Coupon Amount on Checkout Page before Refresh the page  : " + checkoutAmount);
			
			
		   
			driver.navigate().refresh();
			
			
	       }

	  public void checkTheCouponAmountAfterRefresh() {
		

	
	 
		   String popupAmount = getCleanAmount(appliedCouponAmountOnCouponsSection);
			System.out.println("Coupon Amount on Coupon Section after  Refresh the page   : " + popupAmount);
			
			String checkoutAmount = getCleanAmount(appliedCouponAmountOnCheckoutPage);
			System.out.println("Coupon Amount on Checkout Page after  Refresh the page  : " + checkoutAmount);
			
	   }

//16
	  public void CouponAppliedValidationMessage()
	  
	  {
		  
		  Common.waitForElement(5);
			
//		  By successMessage = By.xpath("//div[@class='snackbar-container  snackbar-pos top-right']");
//		    // Wait until the snackbar appears (freshly locating it)
//		    String actualText = wait.until(ExpectedConditions
//		            .visibilityOfElementLocated(successMessage))
//		            .getText().trim();
//		    Assert.assertTrue(":x: Expected success text not found. Actual: " + actualText,
//		            actualText.toLowerCase().contains(expectedText.toLowerCase()));
//		    System.out.println( "\u001B[0m" + "Error message appeared: " + actualText  + "\u001B[0m");
		
	
	  String popupAmount = getCleanAmount(appliedCouponAmountOnCouponsSection);
		System.out.println("Coupon Amount on Coupon Section Before removing   : " + popupAmount);
		
		String checkoutAmount = getCleanAmount(appliedCouponAmountOnCheckoutPage);
		System.out.println("Coupon Amount on Checkout Page Before removing  : " + checkoutAmount);
		
	  }
	
	public void removeCouponFunctionality(String expectedText) {
		
		click(viewCouponButton);
		click(couponPopupRemoveButton);
		
		 By removeMessage = By.xpath("//div[@class='snackbar-container  snackbar-pos top-right']");
		    // Wait until the snackbar appears (freshly locating it)
		    String actualText = wait.until(ExpectedConditions
		            .visibilityOfElementLocated(removeMessage))
		            .getText().trim();
		    Assert.assertTrue(":x: Expected success text not found. Actual: " + actualText,
		            actualText.toLowerCase().contains(expectedText.toLowerCase()));
		    System.out.println( "\u001B[0m" + "Error message appeared: " + actualText  + "\u001B[0m");
		}
	
	public void CouponRemovedOrNot() {
		
		if ((appliedCouponAmountOnCouponsSection.isDisplayed()) || (appliedCouponAmountOnCheckoutPage.isDisplayed())) {
		    System.out.println("❌ Still coupon not removed — Test Case Failed");
		} else {
		    System.out.println("✅ Coupon removed successfully — Test Case Passed");
		}

		

	}

	
	//15
	 
	public void addPropdcutToCart() {
		
		HomePage home = new HomePage(driver);
		home.homeLaunch();
		checkout();
		click(viewCouponButton);
		click(couponPopupApplyButton);		
		wait.until(ExpectedConditions.visibilityOf(loginPopup));
		Assert.assertTrue(" Login popup did not appear!", loginPopup.isDisplayed());
		System.out.println("Login popup appeared successfully!");
		click(signupButton);


	}
	
	
	public void loginusingApplyButtonOnCouponPopup() {
		
		
		 NegativeSignupPages sign = new NegativeSignupPages(driver);
		 wait.until(ExpectedConditions.visibilityOf(signupPopup));
			Assert.assertTrue(" Signup popup did not appear!", signupPopup.isDisplayed());
			System.out.println("Signup  popup appeared successfully!");
		 
		 
		 sign.signUp();
		
		

	}
	//13
	public void clickOnViewCouponButton() {
		
		 click(viewCouponButton);
	}
	
	public void unlockMoreCoupons() {
		
		List<String> couponDetails = new ArrayList<>();

		// Extract coupons from Unlock More Coupons
		if (unlockMoreCoupon.isDisplayed() && !unlockCouponCode.isEmpty() && !unlockCouponName.isEmpty()) {
		    System.out.println("=== Unlock More Coupons Section is Visible ===");
		    for (int i = 0; i < unlockCouponCode.size(); i++) {
		        String code = unlockCouponCode.get(i).getText().trim();
		        String fullText = unlockCouponName.get(i).getText().trim();

		        String[] parts = fullText.split("\n");
		        String name = parts.length > 0 ? parts[0].trim() : "";
		        String description = parts.length > 1 ? parts[1].trim() : "";

		        String formatted = "Code: " + code + " | Name: " + name + " | Description: " + description;
		        couponDetails.add(formatted);

		        System.out.println(formatted);
		        System.out.println("---------------------------------");
		    }
		} 
		// Extract from Available Coupons if Unlock More Coupons is not visible
		else if (unlockMoreCoupon.isDisplayed() && !unlockCouponCode.isEmpty() && !unlockCouponName.isEmpty()) {
		    System.out.println("=== Unlock More Coupons Section is Visible ===");
		    for (int i = 0; i < unlockCouponCode.size(); i++) {
		        String code = unlockCouponCode.get(i).getText().trim();
		        String fullText = unlockCouponName.get(i).getText().trim();

		        String[] parts = fullText.split("\n");
		        String name = parts.length > 0 ? parts[0].trim() : "";
		        String description = parts.length > 1 ? parts[1].trim() : "";

		        String formatted = "Code: " + code + " | Name: " + name + " | Description: " + description;
		        couponDetails.add(formatted);

		        System.out.println(formatted);
		        System.out.println("---------------------------------");
		    }
		} 
		else {
		    System.out.println("No Coupons section is visible.");
		}

		// Compare coupons by formatted strings
		System.out.println("===== Coupon Comparison Results =====");
		for (int i = 0; i < couponDetails.size(); i++) {
		    for (int j = i + 1; j < couponDetails.size(); j++) {
		        if (couponDetails.get(i).equals(couponDetails.get(j))) {
		            System.out.println("Coupon " + (i + 1) + " and Coupon " + (j + 1) + " are SAME.");
		        } else {
		            System.out.println("Coupon " + (i + 1) + " and Coupon " + (j + 1) + " are DIFFERENT.");
		        }
		    }
		}

	}
	
//12	
	
	public void availableCoupon() {
		
		List<String> couponDetails = new ArrayList<>();

		// Extract coupons from Unlock More Coupons
		 if (availableCoupon.isDisplayed() && !availableCouponCode.isEmpty() && !availableCouponName.isEmpty()) {
		    System.out.println("=== Available Coupons Section is Visible ===");
		    for (int i = 0; i < availableCouponCode.size(); i++) {
		        String code = availableCouponCode.get(i).getText().trim();
		        String fullText = availableCouponName.get(i).getText().trim();
		        String[] parts = fullText.split("\n");
		        String name = parts.length > 0 ? parts[0].trim() : "";
		        String description = parts.length > 1 ? parts[1].trim() : "";

		        String formatted = "Code: " + code + " | Name: " + name + " | Description: " + description;
		        couponDetails.add(formatted);

		        System.out.println(formatted);
		        System.out.println("---------------------------------");
		    }
		} 
		// Extract from Available Coupons if Unlock More Coupons is not visible
		else if (availableCoupon.isDisplayed() && !availableCouponCode.isEmpty() && !availableCouponName.isEmpty()) {
		    System.out.println("=== Available Coupons Section is Visible ===");
		    for (int i = 0; i < availableCouponCode.size(); i++) {
		        String code = availableCouponCode.get(i).getText().trim();
		        String fullText = availableCouponName.get(i).getText().trim();

		        String[] parts = fullText.split("\n");
		        String name = parts.length > 0 ? parts[0].trim() : "";
		        String description = parts.length > 1 ? parts[1].trim() : "";

		        String formatted = "Code: " + code + " | Name: " + name + " | Description: " + description;
		        couponDetails.add(formatted);

		        System.out.println(formatted);
		        System.out.println("---------------------------------");
		    }
		} 
		else {
		    System.out.println("No Coupons section is visible.");
		}

		 System.out.println("===== Coupon Comparison Results =====");

		 for (int i = 0; i < couponDetails.size(); i++) {
		     for (int j = 0; j < couponDetails.size(); j++) {
		         if (i != j) { // avoid comparing with itself
		             if (couponDetails.get(i).equals(couponDetails.get(j))) {
		                 System.out.println("Coupon " + (i + 1) + " and Coupon " + (j + 1) + " are SAME.");
		             } else {
		                 System.out.println("Coupon " + (i + 1) + " and Coupon " + (j + 1) + " are DIFFERENT.");
		             }
		         }
		     }
		 }
	}
	

//11
	
	public void specialCouponCodeWithfixedAmount() {
		click(viewCouponButton);	
	     type(couponCodeTextBox, FileReaderManager.getInstance().getJsonReader().getValueFromJson("Special CouponCodeForFixedAmount"));
	     }
	
	public void fixedAmountAppliedSuccessfullForSpecialCoupon() {
		
		  Common.waitForElement(5);

		  String couponSectionCouponAmount = getCleanAmount(appliedCouponAmountOnCouponsSection);
		  System.out.println("Coupon Amount on Coupon Section   : " + couponSectionCouponAmount);

		  String checkoutAmount = getCleanAmount(appliedCouponAmountOnCheckoutPage);
		  System.out.println("Coupon Amount on Checkout Page   : " + checkoutAmount);

		  // Check if amounts match and print accordingly
		  if (couponSectionCouponAmount.equals(checkoutAmount)) {
		      System.out.println("✅ Coupon amounts match: " + couponSectionCouponAmount);
		  } else {
		      System.out.println("❌ Coupon amounts do NOT match!");
		  }

	}
	
	public void specialCouponCodeWithPercentageAmount() {
		click(viewCouponButton);	
	     type(couponCodeTextBox, FileReaderManager.getInstance().getJsonReader().getValueFromJson("SpecialCouponCodeForPercentage"));
	     }
	
	public void PercentageAmountAppliedSuccessfullForSpecialCoupon() {
		
		  Common.waitForElement(5);

		  String couponSectionCouponAmount = getCleanAmount(appliedCouponAmountOnCouponsSection);
		  System.out.println("Coupon Amount on Coupon Section   : " + couponSectionCouponAmount);

		  String checkoutAmount = getCleanAmount(appliedCouponAmountOnCheckoutPage);
		  System.out.println("Coupon Amount on Checkout Page   : " + checkoutAmount);

		  // Check if amounts match and print accordingly
		  if (couponSectionCouponAmount.equals(checkoutAmount)) {
		      System.out.println("✅ Coupon amounts match: " + couponSectionCouponAmount);
		  } else {
		      System.out.println("❌ Coupon amounts do NOT match!");
		  }

	}
	
	public void NormalCouponCodeWithfixedAmount() {

		click(viewCouponButton);	
	     type(couponCodeTextBox, FileReaderManager.getInstance().getJsonReader().getValueFromJson("NormalCouponCodeForFixedAmount"));
	     }
		
	public void fixedAmountAppliedSuccessfullForNormalCoupon() {
		

		  Common.waitForElement(5);

		  String couponSectionCouponAmount = getCleanAmount(appliedCouponAmountOnCouponsSection);
		  System.out.println("Coupon Amount on Coupon Section   : " + couponSectionCouponAmount);

		  String checkoutAmount = getCleanAmount(appliedCouponAmountOnCheckoutPage);
		  System.out.println("Coupon Amount on Checkout Page   : " + checkoutAmount);

		  // Check if amounts match and print accordingly
		  if (couponSectionCouponAmount.equals(checkoutAmount)) {
		      System.out.println("✅ Coupon amounts match: " + couponSectionCouponAmount);
		  } else {
		      System.out.println("❌ Coupon amounts do NOT match!");
		  }

		

	}
		



	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WebDriver gmail(String browserName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isAt() {
		// TODO Auto-generated method stub
		return false;
	}

	

	



	
	
}
