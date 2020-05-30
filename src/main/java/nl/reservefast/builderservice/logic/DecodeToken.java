package nl.reservefast.builderservice.logic;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.UUID;

@Component
public class DecodeToken {
    @Value("${JWT_SECRET_KEY:verysecretkey}")
    private String secretKey;

    public UUID getId(String token) {
        return UUID.fromString(decodeToken(token).getBody().getSubject());
    }

    public Jws<Claims> decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes(Charset.forName("UTF-8")))
                .parseClaimsJws(token);
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else return null;
    }
}
