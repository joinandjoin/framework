package com.huaxia.framework.security.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.util.StringUtils;

/**
 * OIDC 工具类，提供 1、生成秘钥对 2、生成claims 3、生成token 4、校验token 的方法
 * 
 * @author Administrator
 *
 */
public class OidcUtils {
	/**
	 * 加密的位数为2048
	 */
	public static final int BITS = 2048;

	/**
	 * 通过秘钥key和加密算法生成秘钥对 即JWK
	 * 
	 * @param keyId
	 *            String 公钥私钥对 对应的一个唯一 Id,如果为空则用UUID自行生成
	 * @param algorithm
	 *            String 加密算法名称，如果为空，则默认ES256
	 * @param bits
	 *            int 加密算法加密位数，默认2048
	 * @throws JoseException
	 *             如果生成JWK出现错误，则抛出异常
	 */
	public static RsaJsonWebKey generateSecretKeyPair(String keyId, String algorithm, int bits) throws JoseException {
		if (!StringUtils.hasText(keyId)) {
			keyId = String.valueOf(UUID.randomUUID().getMostSignificantBits())
					+ String.valueOf(UUID.randomUUID().getMostSignificantBits());
		}
		if (!StringUtils.hasText(algorithm)) {
			algorithm = AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256;
		}
		if (bits <= 0) {
			bits = BITS;
		}

		RsaJsonWebKey jwk = RsaJwkGenerator.generateJwk(BITS);
		jwk.setKeyId(keyId);
		jwk.setAlgorithm(algorithm);
		return jwk;
	}

	public static RsaJsonWebKey generateSecretKeyPair(String keyId) throws JoseException {
		return generateSecretKeyPair(keyId, null, 0);
	}

	public static RsaJsonWebKey generateSecretKeyPair(String keyId, String algorithm) throws JoseException {
		return generateSecretKeyPair(keyId, algorithm, 0);
	}

	public static RsaJsonWebKey generateSecretKeyPair(String keyId, int bits) throws JoseException {
		return generateSecretKeyPair(keyId, null, bits);
	}

	public static RsaJsonWebKey generateSecretKeyPair() throws JoseException {
		return generateSecretKeyPair(null, null, 0);
	}

	public static String getPrivateKeyJson(JsonWebKey jwk) {
		return jwk.toJson(JsonWebKey.OutputControlLevel.INCLUDE_PRIVATE);
	}

	public static String getPublicKeyJson(JsonWebKey jwk) {
		return jwk.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY);
	}

	/**
	 * 
	 * @param beforeMinutesPast
	 * @param expirationTime
	 * @param producter
	 * @param consumer
	 * @param subject
	 * @param extendClaims
	 * @return
	 */
	public static JwtClaims generateJwtClaims(int beforeMinutesPast, int expirationTime, String producter,
			String consumer, String subject, Map<String, ?> extendClaims) {
		JwtClaims claims = new JwtClaims();
		claims.setGeneratedJwtId();
		claims.setIssuedAtToNow();
		claims.setExpirationTimeMinutesInTheFuture(expirationTime);
		claims.setNotBeforeMinutesInThePast(beforeMinutesPast);
		claims.setIssuer(producter);
		claims.setAudience(consumer);
		claims.setSubject(subject);
		if (extendClaims != null && !extendClaims.isEmpty()) {
			for (Entry<String, ?> entry : extendClaims.entrySet()) {
				claims.setClaim(entry.getKey(), entry.getValue());
			}
		}
		return claims;
	}

	public static String generateIdToke(JsonWebKey jwk, String algorithm, String keyId, JwtClaims claims)
			throws JoseException {
		JsonWebSignature jws = new JsonWebSignature();
		if (!StringUtils.hasText(algorithm)) {
			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
		} else {
			jws.setAlgorithmHeaderValue(algorithm);
		}
		jws.setKeyIdHeaderValue(keyId);
		jws.setPayload(claims.toJson());
		jws.setKey(((RsaJsonWebKey)jwk).getPrivateKey());
		return jws.getCompactSerialization();
	}

	public static String generateIdToke(String privkeyJson, String algorithm, String keyId, JwtClaims claims)
			throws JoseException {
		JsonWebKey jwk = JsonWebKey.Factory.newJwk(privkeyJson);
		return generateIdToke(jwk, algorithm, keyId, claims);
	}

	public static JwtClaims verificationIdToke(JsonWebKey jwk, String algorithm, String idToken, String producter,
			String consumer, int clockSkewInSeconds) throws InvalidJwtException {
		if (!StringUtils.hasText(algorithm)) {
			algorithm = AlgorithmIdentifiers.RSA_USING_SHA256;
		}
		if (clockSkewInSeconds <= 0) {
			clockSkewInSeconds = 30;
		}
		JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime()
				.setAllowedClockSkewInSeconds(clockSkewInSeconds).setRequireSubject().setExpectedIssuer(producter)
				.setExpectedAudience(consumer).setVerificationKey(jwk.getKey())
				.setJwsAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.WHITELIST, algorithm)).build();

		JwtClaims jwtClaims = jwtConsumer.processToClaims(idToken);
		return jwtClaims;

	}

	public static JwtClaims verificationIdToke(String pubKeyJson, String algorithm, String idToken, String producter,
			String consumer, int clockSkewInSeconds) throws InvalidJwtException {
		JsonWebKey jwk;
		try {
			jwk = JsonWebKey.Factory.newJwk(pubKeyJson);
			return verificationIdToke(jwk, algorithm, idToken, producter, consumer, clockSkewInSeconds);
		} catch (JoseException e) {
			throw new InvalidJwtException(e.getMessage());
		}
	}
	
	public static void main(String[] args) throws JoseException, InvalidJwtException, MalformedClaimException {
		RsaJsonWebKey jwk = generateSecretKeyPair("sudai_app");
		String privkey = getPrivateKeyJson(jwk);
		String pubkey = getPublicKeyJson(jwk);
		String subject = 
				"{\"head\":{\"version\":\"1.0\",\"channel\":\"consumer-front\",\"device\":\"android\",\"tradeTime\":\"\",\"flowID\":\"2016091322492600008\",\"tradeCode\":\"0009\",\"tradeType\":\"appService\",\"msgType\":\"json\",\"session\":\"\",\"token\":\"\",\"responseTime\":\"\",\"responseCode\":\"\",\"responseMsg\":\"\",\"tradeStatus\":\"\",\"operatorID\":\"test\"},\"body\":{\"id_card\":\"310115198709118639\",\"name\":\"张慧晶\",\"phone_number\":\"13816676617\"}}";

		JwtClaims jwtClaims = generateJwtClaims(2, 30, "huaxiaapp", "cai", "abcded", null);
		String idToken = generateIdToke(jwk, null, "sudai_app", jwtClaims);
		
		System.out.println(privkey);
		System.out.println(pubkey);
		System.out.println(idToken);
		
		JwtClaims vered = verificationIdToke(pubkey, null, idToken,"huaxiaapp","cai",30);
		
		String body = vered.getSubject();
		System.out.println(body);
	}
}
