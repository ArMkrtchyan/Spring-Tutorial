package com.armkrtchyan.tutorial.configurations.jwt

import com.armkrtchyan.tutorial.entity.UserEntity
import com.armkrtchyan.tutorial.service.UserService
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider {
    private var secretKey = "aslkjedomjlwsdk"
    private val validityInMilliseconds: Long = 360000000 // 1h

    @Autowired
    private val userDetailsService: UserService? = null

    fun createToken(phone: String?): String {

        val claims = Jwts.claims().setSubject(phone)
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        return Jwts.builder() //
                .setClaims(claims) //
                .signWith(SignatureAlgorithm.HS256, secretKey) //
                .compact()
    }

    fun getAuthentication(token: String?): Authentication {
        val userDetails = userDetailsService?.loadUserByUsername(getPhone(token)) as UserEntity
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getPhone(token: String?): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    fun saveHeadersInfo(req: HttpServletRequest?) {
        try { //            Device device = new Device();
//            device.setUid(req.getHeader("X-device-id"));
//            device.setApp_version(req.getHeader("X-version"));
//            device.setLanguage(req.getHeader("lang"));
//            device.setSdk_version(req.getHeader("X-version-sdk"));
//            device.setModel(req.getHeader("X-model"));
//            device.setPlatform(req.getHeader("X-platform"));
//            device.setFirst_install_date(req.getHeader("X-install-date"));
//            LoggerFactory.getLogger("JwtFilter").info(device.toString());
//            LoggerFactory.getLogger("JwtFilter").info("" + deviceDao);
//            LoggerFactory.getLogger("JwtFilter").info("" + deviceDao.existsByUid(device.getUid()));
//            if (device.getUid() != null) {
//                if (deviceDao.existsByUid(device.getUid())) {
//                    deviceDao.updateDeviceByUid(device.getUid(), device.getApp_version(), device.getFirst_install_date(), device.getLanguage(),
//                            device.getModel(), device.getPlatform(), device.getSdk_version());
//                } else {
//                    deviceDao.saveAndFlush(device);
//                }
//            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun validateToken(token: String?): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            //            if (claims.getBody().getExpiration().before(new Date())) {
//                return false;
//            }
            true
        } catch (e: JwtException) {
            throw InvalidJwtAuthenticationException("Expired or invalid JWT token")
        } catch (e: IllegalArgumentException) {
            throw InvalidJwtAuthenticationException("Expired or invalid JWT token")
        }
    }
}