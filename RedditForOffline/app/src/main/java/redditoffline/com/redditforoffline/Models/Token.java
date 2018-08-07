package redditoffline.com.redditforoffline.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {

  @SerializedName("access_token")
  @Expose
  private String accessToken;
  @SerializedName("token_type")
  @Expose
  private String tokenType;
  @SerializedName("device_id")
  @Expose
  private String deviceId;
  @SerializedName("expires_in")
  @Expose
  private int expiresIn;
  @SerializedName("scope")
  @Expose
  private String scope;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

}