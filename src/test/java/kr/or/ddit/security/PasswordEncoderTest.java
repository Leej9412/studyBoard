package kr.or.ddit.security;

import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.AbstractModelLayerTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordEncoderTest extends AbstractModelLayerTest{
//	@Inject
	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Test
	public void encodeTest() {
		String plain = "java";
		// encrypt(암호화)/decrypt vs encode(부호화)/decode
		String encoded = encoder.encode(plain);
		log.info("encoded password : {}", encoded);
		
		String saved = "{bcrypt}$2a$10$lAnMt7pqbPKvkYMi6XdeLeXQRqvtu9KW2ildCM.q5oCkL8nGDwkLm";
		log.info("인증 성공 여부 : {}", encoder.matches(plain, saved));
	}
}
