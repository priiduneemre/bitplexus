package com.neemre.bitplexus.backend.crypto.adapter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.neemre.bitplexus.backend.crypto.BitcoinWrapperException;
import com.neemre.bitplexus.backend.crypto.LitecoinWrapperException;
import com.neemre.bitplexus.backend.model.Address;
import com.neemre.bitplexus.backend.model.Address.EncodedFormExtractor;
import com.neemre.bitplexus.common.Defaults;
import com.neemre.bitplexus.common.Errors;
import com.neemre.bitplexus.common.PropertyKeys;
import com.neemre.bitplexus.common.dto.virtual.PaymentDetailsDto;
import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.ltcdcli4j.core.LitecoindException;
import com.neemre.ltcdcli4j.core.client.LtcdClient;

@Component
public class NodeClientAdapter extends NodeWrapperAdapter {

	public String createBtcRawTransaction(PaymentDetailsDto paymentDetailsDto,
			List<com.neemre.btcdcli4j.core.domain.OutputOverview> unspentOutputs, String chainCode) 
					throws BitcoinWrapperException {
		try {
			return wrapperResolver.getBtcdClient(chainCode).createRawTransaction(unspentOutputs, 
					ImmutableMap.of(paymentDetailsDto.getRecipientAddress(), paymentDetailsDto
							.getAmount()));
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	public String createLtcRawTransaction(PaymentDetailsDto paymentDetailsDto,
			List<com.neemre.ltcdcli4j.core.domain.OutputOverview> unspentOutputs, String chainCode) 
					throws LitecoinWrapperException {
		try {
			return wrapperResolver.getLtcdClient(chainCode).createRawTransaction(unspentOutputs, 
					ImmutableMap.of(paymentDetailsDto.getRecipientAddress(), paymentDetailsDto
							.getAmount()));
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	public BigDecimal getBtcAddressBalance(String address, String chainCode) 
			throws BitcoinWrapperException {
		try {
			List<com.neemre.btcdcli4j.core.domain.Output> unspentOutputs = wrapperResolver
					.getBtcdClient(chainCode).listUnspent(Defaults.BTC_COMPLETED_CONF_COUNT,
							Integer.MAX_VALUE, Arrays.asList(address));
			BigDecimal balance = BigDecimal.ZERO;
			for (int i = 0; i < unspentOutputs.size(); i++) {
				balance = balance.add(unspentOutputs.get(i).getAmount());
			}
			return balance;
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	public BigDecimal getLtcAddressBalance(String address, String chainCode) 
			throws LitecoinWrapperException {
		try {
			List<com.neemre.ltcdcli4j.core.domain.Output> unspentOutputs = wrapperResolver
					.getLtcdClient(chainCode).listUnspent(Defaults.LTC_COMPLETED_CONF_COUNT, 
							Integer.MAX_VALUE, Arrays.asList(address));
			BigDecimal balance = BigDecimal.ZERO;
			for (int i = 0; i < unspentOutputs.size(); i++) {
				balance = balance.add(unspentOutputs.get(i).getAmount());
			}
			return balance;
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	public com.neemre.btcdcli4j.core.domain.Block getBtcBlock(Integer blockHeight, String chainCode) 
			throws BitcoinWrapperException {
		try {
			BtcdClient btcdClient = wrapperResolver.getBtcdClient(chainCode);
			return (com.neemre.btcdcli4j.core.domain.Block)btcdClient.getBlock(
					btcdClient.getBlockHash(blockHeight), Defaults.BLOCK_VERBOSITY);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	public com.neemre.ltcdcli4j.core.domain.Block getLtcBlock(Integer blockHeight, String chainCode) 
			throws LitecoinWrapperException {
		try {
			LtcdClient ltcdClient = wrapperResolver.getLtcdClient(chainCode);
			return (com.neemre.ltcdcli4j.core.domain.Block)ltcdClient.getBlock(
					ltcdClient.getBlockHash(blockHeight), Defaults.BLOCK_VERBOSITY);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	public com.neemre.btcdcli4j.core.domain.Block getBtcBlock(String headerHash, String chainCode) 
			throws BitcoinWrapperException {
		try {
			return (com.neemre.btcdcli4j.core.domain.Block)wrapperResolver.getBtcdClient(chainCode)
					.getBlock(headerHash, Defaults.BLOCK_VERBOSITY);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	public com.neemre.ltcdcli4j.core.domain.Block getLtcBlock(String headerHash, String chainCode) 
			throws LitecoinWrapperException {
		try {
			return (com.neemre.ltcdcli4j.core.domain.Block)wrapperResolver.getLtcdClient(chainCode)
					.getBlock(headerHash, Defaults.BLOCK_VERBOSITY);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	public String getBtcNewAddress(String chainCode) throws BitcoinWrapperException {
		try {
			return wrapperResolver.getBtcdClient(chainCode).getNewAddress();
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	public String getLtcNewAddress(String chainCode) throws LitecoinWrapperException {
		try {
			return wrapperResolver.getLtcdClient(chainCode).getNewAddress();
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	public com.neemre.btcdcli4j.core.domain.RawTransaction getBtcRawTransaction(String networkUid,
			String chainCode) throws BitcoinWrapperException {
		try {
			return (com.neemre.btcdcli4j.core.domain.RawTransaction)wrapperResolver.getBtcdClient(
					chainCode).getRawTransaction(networkUid, Defaults.RAW_TXN_VERBOSITY);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	public com.neemre.ltcdcli4j.core.domain.RawTransaction getLtcRawTransaction(String networkUid,
			String chainCode) throws LitecoinWrapperException {
		try {
			return (com.neemre.ltcdcli4j.core.domain.RawTransaction)wrapperResolver.getLtcdClient(
					chainCode).getRawTransaction(networkUid, Defaults.RAW_TXN_VERBOSITY);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	public com.neemre.btcdcli4j.core.domain.Transaction getBtcTransaction(String networkUid,
			String chainCode) throws BitcoinWrapperException {
		try {
			return wrapperResolver.getBtcdClient(chainCode).getTransaction(networkUid);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	public com.neemre.ltcdcli4j.core.domain.Transaction getLtcTransaction(String networkUid,
			String chainCode) throws LitecoinWrapperException {
		try {
			return wrapperResolver.getLtcdClient(chainCode).getTransaction(networkUid);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	public List<com.neemre.btcdcli4j.core.domain.Output> getBtcUnspentOutputs(List<Address> addresses,
			String chainCode) throws BitcoinWrapperException {
		try {
			List<String> encodedForms = Lists.transform(addresses, new EncodedFormExtractor());
			return wrapperResolver.getBtcdClient(chainCode).listUnspent(
					Defaults.BTC_COMPLETED_CONF_COUNT, Integer.MAX_VALUE, encodedForms);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	public List<com.neemre.ltcdcli4j.core.domain.Output> getLtcUnspentOutputs(List<Address> addresses, 
			String chainCode) throws LitecoinWrapperException {
		try {
			List<String> encodedForms = Lists.transform(addresses, new EncodedFormExtractor());
			return wrapperResolver.getLtcdClient(chainCode).listUnspent(
					Defaults.LTC_COMPLETED_CONF_COUNT, Integer.MAX_VALUE, encodedForms);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	public String sendBtcRawTransaction(String signedHexTransaction, String chainCode)
			throws BitcoinWrapperException {
		try {
			return wrapperResolver.getBtcdClient(chainCode).sendRawTransaction(signedHexTransaction,
					Defaults.ALLOW_HIGH_FEES);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	public String sendLtcRawTransaction(String signedHexTransaction, String chainCode) 
			throws LitecoinWrapperException {
		try {
			return wrapperResolver.getLtcdClient(chainCode).sendRawTransaction(signedHexTransaction,
					Defaults.ALLOW_HIGH_FEES);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	public void setBtcWalletPassphrase(String passphrase, String chainCode) 
			throws BitcoinWrapperException {
		try {
			wrapperResolver.getBtcdClient(chainCode).walletPassphrase(passphrase, 
					Defaults.PASSPHRASE_TIMEOUT);
		} catch (BitcoindException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
			throw new BitcoinWrapperException(Errors.TODO, e);
		}
	}

	public void setLtcWalletPassphrase(String passphrase, String chainCode) 
			throws LitecoinWrapperException {
		try {
			wrapperResolver.getLtcdClient(chainCode).walletPassphrase(passphrase, 
					Defaults.PASSPHRASE_TIMEOUT);
		} catch (LitecoindException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
			throw new LitecoinWrapperException(Errors.TODO, e);
		}
	}

	public String signBtcRawTransaction(String hexTransaction, String chainCode) 
			throws BitcoinWrapperException {
		BtcdClient btcdClient = wrapperResolver.getBtcdClient(chainCode); 
		int failedAttempts = 0;
		while (true) {
			try {
				setBtcWalletPassphrase(btcdClient.getNodeConfig().getProperty(
						PropertyKeys.BITCOIND_WALLET_PASSPHRASE.getValue()), chainCode);
				return btcdClient.signRawTransaction(hexTransaction).getHex();
			} catch (BitcoindException e) {
				failedAttempts++;
				if (failedAttempts >= Defaults.BTC_TXN_MAX_SIG_ATTEMPTS) {
					throw new BitcoinWrapperException(Errors.TODO, e);
				}
			} catch (com.neemre.btcdcli4j.core.CommunicationException e) {
				throw new BitcoinWrapperException(Errors.TODO, e);
			}
		}
	}

	public String signLtcRawTransaction(String hexTransaction, String chainCode)
			throws LitecoinWrapperException {
		LtcdClient ltcdClient = wrapperResolver.getLtcdClient(chainCode);
		int failedAttempts = 0;
		while (true) {
			try {
				setLtcWalletPassphrase(ltcdClient.getNodeConfig().getProperty(
						PropertyKeys.LITECOIND_WALLET_PASSPHRASE.getValue()), chainCode);
				return ltcdClient.signRawTransaction(hexTransaction).getHex();
			} catch (LitecoindException e) {
				failedAttempts++;
				if (failedAttempts >= Defaults.LTC_TXN_MAX_SIG_ATTEMPTS) {
					throw new LitecoinWrapperException(Errors.TODO, e);
				}
			} catch (com.neemre.ltcdcli4j.core.CommunicationException e) {
				throw new LitecoinWrapperException(Errors.TODO, e);
			}
		}
	}
}