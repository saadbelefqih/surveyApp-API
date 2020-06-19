package ma.surveyapp.security;

public interface SecurityParams {
	public static final String JWT_HEADER_NAME= "Authorization";
	public static final String JWT_SECRET= "s@BELEFQIH";
	public static final String JWT_HEADER_PREFIX="Bearer "; 
	public static final long JWT_EXPIRATION_IN_MS = 86400000 /** 1 day = 1*24*3600*1000*/;
}
