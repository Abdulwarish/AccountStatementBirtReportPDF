package com.account.statement.utils;

import java.time.LocalDate;

import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.account.statement.constants.KeyConstants;

public class BirtParameters {
	
	public static IRunAndRenderTask birtParameterSetting(int randomReferenceNum, IRunAndRenderTask task, String jsonValue, String templateId) throws JSONException {
			JSONObject jsonObject = new JSONObject(jsonValue);
			task.setParameterValue(KeyConstants.CUSTOMER_DETAILS, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.CUSTOMER_DETAILS));
			task.setParameterValue(KeyConstants.ACCOUNT_SUMMARY, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.ACCOUNT_SUMMARY));
			task.setParameterValue(KeyConstants.BIGINING_BALANCE_AMOUNT, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.BIGINING_BALANCE_AMOUNT));
			task.setParameterValue(KeyConstants.DEPOSIT_AND_OTHER_CREDITS, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.DEPOSIT_AND_OTHER_CREDITS));
			task.setParameterValue(KeyConstants.ATM_WITHDRAW, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.ATM_WITHDRAW));
			task.setParameterValue(KeyConstants.VISA_CHECK, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.VISA_CHECK));
			task.setParameterValue(KeyConstants.WITHDRAW_AND_OTHER_DEBIT, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.WITHDRAW_AND_OTHER_DEBIT));
			task.setParameterValue(KeyConstants.CHECKS_PAID, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.CHECKS_PAID));
			task.setParameterValue(KeyConstants.ENDING_BALANCE_ON_DATE, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.ENDING_BALANCE_ON_DATE));
			task.setParameterValue(KeyConstants.CHECKS_PAIDS_ACCOUNT, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.CHECKS_PAIDS_ACCOUNT));
			task.setParameterValue(KeyConstants.TOTAL_CHECKS_PAID, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.TOTAL_CHECKS_PAID));
			task.setParameterValue(KeyConstants.ENDING_BALANCE_ON_VALUE, jsonObject.getJSONObject(KeyConstants.DATA).getString(KeyConstants.ENDING_BALANCE_ON_VALUE));
			task.setParameterValue(KeyConstants.TODAY_DATE, LocalDate.now().toString());
			task.setParameterValue(KeyConstants.UNIQUE_REFERENCE_NUMBER, randomReferenceNum);
		return task;
	}
}
