package com.neemre.bitplexus.backend;

import javax.xml.bind.DatatypeConverter;

public class IncubatorMain {

	public static void main(String[] args) {
		byte[] btcNetworkTransaction = DatatypeConverter.parseHexBinary("01000000013a09dc893059e29d"
				+ "50fe925ea1e312ec29e7922daf58edadfe6ba2a858306eff000000006b483045022100e031948851"
				+ "bac342fc5191950078311da91d7ba2b82765ef67454d9777fa9cb702207bad45abc6b95fef85fde9"
				+ "e7523812a845024b0a6ae4178212d38bafb09411600121039234983a3f8cc00e0c74ea4c01baabcd"
				+ "eef71eef94ed5d1ef54c286f77b97a90ffffffff0120402c00000000001976a914890df84975afa5"
				+ "9299eb505f2919a6f4d9115dd088ac00000000");
		System.out.printf("Network ('%s') transaction size was: '%s';\n", "BITCOIN_TEST3", 
				btcNetworkTransaction.length);
	}
}