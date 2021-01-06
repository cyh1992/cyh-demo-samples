package jwtdemo.jwtdemo.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author cyh
 * @date 2020/12/12 18:48
 */
public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwtToken = request.getHeader("authorization");
        Jws<Claims> jws = Jwts.parser().setSigningKey("chenyuhu").parseClaimsJws(jwtToken.replace("Bearer", ""));
        Claims body = jws.getBody();
        String userName = body.getSubject();
        List<GrantedAuthority> authrorites = AuthorityUtils.commaSeparatedStringToAuthorityList((String) body.get("authrorites"));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, null, authrorites);
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
