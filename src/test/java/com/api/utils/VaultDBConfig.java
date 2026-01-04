package com.api.utils;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

public class VaultDBConfig {
	private static final Logger LOGGER = LogManager.getLogger(VaultDBConfig.class);
	private static VaultConfig vaultConfig;
	private static Vault vault;
	
	static {
		 try {
			vaultConfig = new VaultConfig().address(System.getenv("VAULT_SERVER"))
					.token(System.getenv("VAULT_TOKEN"))
					.build();
		 } catch (VaultException e) {
			 LOGGER.error("Something went wrong with the vault config",e);
			e.printStackTrace();
		 }
		 vault = new Vault(vaultConfig);
	}
	
	private VaultDBConfig()
	{
		
	}
	public static String getSecret(String key)
	{
		LogicalResponse response = null;
		try {
			response = vault.logical().read("secret/phoenix/qa/database");
		} catch (VaultException e) {
			 LOGGER.error("Something went wrong with reading of vault data",e);
			e.printStackTrace();
			return null;
		}
		Map<String,String> data = response.getData();
		String secretValue = data.get(key);
		LOGGER.info("Able to read secrets from vault DB");
		return secretValue;
	}

}
