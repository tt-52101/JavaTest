package com.us.geoip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yyb
 * @time 2019/7/19
 */
public class IpCaculate {
    /**
     * 根据掩码位数计算掩码
     *
     * @param maskIndex 掩码位
     * @return 子网掩码
     */
    public static String getNetMask(String maskIndex) {
        StringBuilder mask = new StringBuilder();
        Integer inetMask = 0;
        try {
            inetMask = Integer.parseInt(maskIndex);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return null;
        }
        if (inetMask > 32) {
            return null;
        }
        // 子网掩码为1占了几个字节
        int num1 = inetMask / 8;
        // 子网掩码的补位位数
        int num2 = inetMask % 8;
        int array[] = new int[4];
        for (int i = 0; i < num1; i++) {
            array[i] = 255;
        }
        for (int i = num1; i < 4; i++) {
            array[i] = 0;
        }
        for (int i = 0; i < num2; num2--) {
            array[num1] += 1 << 8 - num2;
        }
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                mask.append(array[i]);
            } else {
                mask.append(array[i] + ".");
            }
        }
        return mask.toString();
    }

    /**
     * 根据网段计算起始IP 网段格式:x.x.x.x/x
     * 一个网段0一般为网络地址,255一般为广播地址.
     * 起始IP计算:网段与掩码相与之后加一的IP地址
     *
     * @param segment 网段
     * @return 起始IP
     */
    public static String getStartIp(String segment) {
        StringBuffer startIp = new StringBuffer();
        if (segment == null) {
            return null;
        }
        String arr[] = segment.split("/");
        String ip = arr[0];
        String maskIndex = arr[1];
        String mask = IpCaculate.getNetMask(maskIndex);
        if (4 != ip.split("\\.").length || mask == null) {
            return null;
        }
        int ipArray[] = new int[4];
        int netMaskArray[] = new int[4];
        for (int i = 0; i < 4; i++) {
            try {
                ipArray[i] = Integer.parseInt(ip.split("\\.")[i]);
                netMaskArray[i] = Integer.parseInt(mask.split("\\.")[i]);
                if (ipArray[i] > 255 || ipArray[i] < 0 || netMaskArray[i] > 255 || netMaskArray[i] < 0) {
                    return null;
                }
                ipArray[i] = ipArray[i] & netMaskArray[i];
                if (i == 3) {
                    startIp.append(ipArray[i] + 1);
                } else {
                    startIp.append(ipArray[i] + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return startIp.toString();
    }

    /**
     * 根据网段计算结束IP
     *
     * @param segment
     * @return 结束IP
     */
    public static String getEndIp(String segment) {
        StringBuffer endIp = new StringBuffer();
        String startIp = getStartIp(segment);
        if (segment == null) {
            return null;
        }
        String arr[] = segment.split("/");
        String maskIndex = arr[1];
        //实际需要的IP个数
        int hostNumber = 0;
        int startIpArray[] = new int[4];
        try {
            hostNumber = 1 << 32 - (Integer.parseInt(maskIndex));
            for (int i = 0; i < 4; i++) {
                startIpArray[i] = Integer.parseInt(startIp.split("\\.")[i]);
                if (i == 3) {
                    startIpArray[i] = startIpArray[i] - 1;
                    break;
                }
            }
            startIpArray[3] = startIpArray[3] + (hostNumber - 1);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        if (startIpArray[3] > 255) {
            int k = startIpArray[3] / 256;
            startIpArray[3] = startIpArray[3] % 256;
            startIpArray[2] = startIpArray[2] + k;
        }
        if (startIpArray[2] > 255) {
            int j = startIpArray[2] / 256;
            startIpArray[2] = startIpArray[2] % 256;
            startIpArray[1] = startIpArray[1] + j;
            if (startIpArray[1] > 255) {
                int k = startIpArray[1] / 256;
                startIpArray[1] = startIpArray[1] % 256;
                startIpArray[0] = startIpArray[0] + k;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                startIpArray[i] = startIpArray[i] - 1;
            }
            if ("" == endIp.toString() || endIp.length() == 0) {
                endIp.append(startIpArray[i]);
            } else {
                endIp.append("." + startIpArray[i]);
            }
        }
        return endIp.toString();
    }


    /**
     * IP转换为long
     *
     * @param ip
     * @return
     */
    public static final long ipToLong(final String ip) {
        if (null == ip) {
            return 0L;
        }
        Pattern r = Pattern.compile("(\\d{1,3}\\.){3}\\d{1,3}");
        Matcher m = r.matcher(ip);
        if (!m.find()) {
            return 0L;
        }
        final String[] ipNums = ip.split("\\.");

        return (Long.parseLong(ipNums[0]) << 24)
                + (Long.parseLong(ipNums[1]) << 16)
                + (Long.parseLong(ipNums[2]) << 8)
                + (Long.parseLong(ipNums[3]));
    }

    /**
     * long 型ip 转化 字符串型ip 127.0.0.1
     * @param ip
     * @return
     */
    public static final String longToIp(final Long ip) {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(ip >>> 24)).append(".");
        sb.append(String.valueOf((ip & 0x00FFFFFF) >>> 16)).append(".");
        sb.append(String.valueOf((ip & 0x0000FFFF) >>> 8)).append(".");
        sb.append(String.valueOf(ip & 0x000000FF));
        return sb.toString();
    }


}
