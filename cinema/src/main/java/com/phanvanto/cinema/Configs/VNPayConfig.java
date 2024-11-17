package com.phanvanto.cinema.Configs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.phanvanto.cinema.Util.VNPayUtil;

import lombok.Getter;




@Configuration
public class VNPayConfig{
	@Getter
    @Value("${vnpay.pay_url}")
    private String vnp_PayUrl;
    
    @Getter
    @Value("${vnpay.return_url}")
    private String vnp_ReturnUrl;
    
    @Getter
    @Value("${vnpay.tmn_code}")
    private String vnp_TmnCode;
    
    @Getter
    @Value("${vnpay.secret_key}")
    private String secretKey;
    
    @Getter
    @Value("${vnpay.version}")
    private String vnp_Version;
    
    @Getter
    @Value("${vnpay.command}")
    private String vnp_Command;
    
    @Getter
    @Value("${vnpay.order_type}")
    private String orderType;
    // Getter cho vnp_PayUrl
    public String getVnp_PayUrl() {
        return vnp_PayUrl;
    }

    // Getter cho secretKey
    public String getSecretKey() {
        return secretKey;
    }

    public Map<String, String> getVNPayConfig() {
    	  Map<String, String> vnpParamsMap = new HashMap<>();
          vnpParamsMap.put("vnp_Version", this.vnp_Version);
          vnpParamsMap.put("vnp_Command", this.vnp_Command);
          vnpParamsMap.put("vnp_TmnCode", this.vnp_TmnCode);
          vnpParamsMap.put("vnp_CurrCode", "VND");
          vnpParamsMap.put("vnp_TxnRef", VNPayUtil.getRandomNumber(8));
          vnpParamsMap.put("vnp_OrderInfo", "Thanh toan don hang:" + VNPayUtil.getRandomNumber(8));
          vnpParamsMap.put("vnp_OrderType", this.orderType);
          vnpParamsMap.put("vnp_Locale", "vn");
          vnpParamsMap.put("vnp_ReturnUrl", this.vnp_ReturnUrl);
          Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
          SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
          String vnpCreateDate = formatter.format(calendar.getTime());
          vnpParamsMap.put("vnp_CreateDate", vnpCreateDate);
          calendar.add(Calendar.MINUTE, 15);
          String vnp_ExpireDate = formatter.format(calendar.getTime());
          vnpParamsMap.put("vnp_ExpireDate", vnp_ExpireDate);
          return vnpParamsMap;
    }
}
