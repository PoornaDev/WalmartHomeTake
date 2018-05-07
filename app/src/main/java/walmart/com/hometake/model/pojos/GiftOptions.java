package walmart.com.hometake.model.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GiftOptions implements Serializable {

@SerializedName("allowGiftWrap")
@Expose
private boolean allowGiftWrap;
@SerializedName("allowGiftMessage")
@Expose
private boolean allowGiftMessage;
@SerializedName("allowGiftReceipt")
@Expose
private boolean allowGiftReceipt;

public boolean isAllowGiftWrap() {
return allowGiftWrap;
}

public void setAllowGiftWrap(boolean allowGiftWrap) {
this.allowGiftWrap = allowGiftWrap;
}

public boolean isAllowGiftMessage() {
return allowGiftMessage;
}

public void setAllowGiftMessage(boolean allowGiftMessage) {
this.allowGiftMessage = allowGiftMessage;
}

public boolean isAllowGiftReceipt() {
return allowGiftReceipt;
}

public void setAllowGiftReceipt(boolean allowGiftReceipt) {
this.allowGiftReceipt = allowGiftReceipt;
}

}