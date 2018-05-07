package walmart.com.hometake.model.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Attributes implements Serializable {

@SerializedName("isReplenishable")
@Expose
private String isReplenishable;
@SerializedName("ppuQtyUom")
@Expose
private String ppuQtyUom;
@SerializedName("wklyFcstWeeks")
@Expose
private String wklyFcstWeeks;

public String getIsReplenishable() {
return isReplenishable;
}

public void setIsReplenishable(String isReplenishable) {
this.isReplenishable = isReplenishable;
}

public String getPpuQtyUom() {
return ppuQtyUom;
}

public void setPpuQtyUom(String ppuQtyUom) {
this.ppuQtyUom = ppuQtyUom;
}

public String getWklyFcstWeeks() {
return wklyFcstWeeks;
}

public void setWklyFcstWeeks(String wklyFcstWeeks) {
this.wklyFcstWeeks = wklyFcstWeeks;
}

}