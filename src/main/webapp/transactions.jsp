<%@ page import="org.mcmasterkboys.codenamehenryford.objects.User" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="me.hy.libhyextended.sql.SQLConnection" %>
<%@ page import="org.mcmasterkboys.codenamehenryford.modules.SQLConnectionFactory" %>
<%@ page import="org.mcmasterkboys.codenamehenryford.objects.banking.Transactions" %><%--
  Created by IntelliJ IDEA.
  User: hoyounsong
  Date: 2023-01-15
  Time: 10:55 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Transactions</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <style>
    html,
    body {
      display: flex;
      flex-direction: column;
      flex: 1;
      width: 100%;
      height: 100%;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
    }
  </style>
</head>
<%
  User u = (User) session.getAttribute("user");
  if (u == null) {
    response.sendRedirect("login.jsp");
    return;
  }


%>
<body>
<div class="w-[1440px] h-[1024px] relative overflow-hidden bg-white">
  <p class="w-[147px] h-5 absolute left-[317px] top-24 text-2xl font-medium text-left text-[#666]"> Transactions </p>
  <div class="w-[281.87px] h-[1014.6px]">
    <div class="w-[281.87px] h-[68.4px]">
      <div class="w-[281.87px] h-[68.4px] absolute left-[-0.5px] top-[-0.5px] bg-white"></div>
    </div>
    <div class="w-[281.87px] h-[955.6px] absolute left-[-0.5px] top-[58.5px] bg-white"></div>
    <div class="flex flex-col justify-start items-start w-[169.53px] h-[320.81px] absolute left-[53.11px] top-[109.45px] gap-[50px]">
      <div class="flex-grow-0 flex-shrink-0 w-[134px] h-6">
        <p class="absolute left-11 top-0 text-xl text-left text-neutral-600">Overview</p>
        <svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 absolute left-0 top-0" preserveAspectRatio="xMidYMid meet">
          <g clip-path="url(#clip0_24_2027)">
            <path d="M6.60645 10.4456H4.60645V17.4456H6.60645V10.4456ZM12.6064 10.4456H10.6064V17.4456H12.6064V10.4456ZM21.1064 19.4456H2.10645V21.4456H21.1064V19.4456ZM18.6064 10.4456H16.6064V17.4456H18.6064V10.4456ZM11.6064 3.70556L16.8164 6.44556H6.39645L11.6064 3.70556ZM11.6064 1.44556L2.10645 6.44556V8.44556H21.1064V6.44556L11.6064 1.44556Z" fill="#525252"></path>
          </g>
          <defs>
            <clipPath id="clip0_24_2027">
              <rect width="24" height="24" fill="white" transform="translate(0.106445 0.445557)"></rect>
            </clipPath>
          </defs>
        </svg>
      </div>
      <div class="flex-grow-0 flex-shrink-0 w-[99px] h-[25px]">
        <p class="absolute left-11 top-[75px] text-xl text-left text-[#666]">Users</p>
        <svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 absolute left-0 top-[74px]" preserveAspectRatio="xMidYMid meet">
          <path d="M17.902 21.1515C17.5602 20.0883 16.8069 19.1488 15.759 18.4788C14.7111 17.8087 13.4273 17.4456 12.1064 17.4456C10.7856 17.4456 9.50174 17.8087 8.45388 18.4788C7.40601 19.1488 6.65274 20.0883 6.31089 21.1515" stroke="#666666" stroke-width="2"></path>
          <circle cx="12.1064" cy="10.4456" r="3" stroke="#666666" stroke-width="2" stroke-linecap="round"></circle>
          <rect x="3.10645" y="3.44556" width="18" height="18" rx="3" stroke="#666666" stroke-width="2"></rect>
        </svg>
      </div>
      <div class="flex-grow-0 flex-shrink-0 w-[166px] h-6">
        <p class="absolute left-11 top-[149px] text-xl text-left text-[#003443]"> Transactions </p>
        <svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 absolute left-0 top-[149px]" preserveAspectRatio="xMidYMid meet">
          <g clip-path="url(#clip0_24_2044)">
            <path d="M2.85669 0.445557V24.4456H5.31241V23.611C5.31241 23.1928 5.65259 22.8527 6.07072 22.8527C6.48884 22.8527 6.82903 23.1928 6.82903 23.611V24.4456H9.33626V23.611C9.33626 23.1928 9.67642 22.8527 10.0945 22.8527C10.5126 22.8527 10.8528 23.1928 10.8528 23.611V24.4456H13.3601V23.611C13.3601 23.1928 13.7002 22.8527 14.1183 22.8527C14.5364 22.8527 14.8766 23.1928 14.8766 23.611V24.4456H17.3839V23.611C17.3839 23.1928 17.724 22.8527 18.1421 22.8527C18.5603 22.8527 18.9004 23.1928 18.9004 23.611V24.4456H21.3561V0.445557H18.9005V1.28016C18.9005 1.69829 18.5603 2.03845 18.1422 2.03845C17.7241 2.03845 17.3839 1.69829 17.3839 1.28016V0.445557H14.8766V1.28016C14.8766 1.69829 14.5365 2.03845 14.1184 2.03845C13.7003 2.03845 13.3601 1.69829 13.3601 1.28016V0.445557H10.8528V1.28016C10.8528 1.69829 10.5126 2.03845 10.0945 2.03845C9.67644 2.03845 9.33626 1.69829 9.33626 1.28016V0.445557H6.82903V1.28016C6.82903 1.69829 6.48884 2.03845 6.07072 2.03845C5.65259 2.03845 5.31241 1.69829 5.31241 1.28016V0.445557H2.85669ZM6.07072 3.70766C6.90697 3.70766 7.64589 3.28252 8.08266 2.6371C8.51942 3.28252 9.25829 3.70766 10.0946 3.70766C10.9309 3.70766 11.6698 3.28263 12.1065 2.6371C12.5433 3.28263 13.2821 3.70766 14.1184 3.70766C14.9548 3.70766 15.6936 3.2826 16.1304 2.6371C16.5671 3.28263 17.3059 3.70766 18.1423 3.70766C18.7286 3.70766 19.2671 3.4987 19.6871 3.15135V21.7398C19.2671 21.3924 18.7286 21.1835 18.1423 21.1835C17.3059 21.1835 16.5671 21.6085 16.1304 22.254C15.6936 21.6085 14.9548 21.1835 14.1184 21.1835C13.2821 21.1835 12.5432 21.6085 12.1065 22.254C11.6697 21.6085 10.9309 21.1835 10.0945 21.1835C9.25832 21.1835 8.5194 21.6086 8.08263 22.254C7.64586 21.6086 6.90697 21.1835 6.07069 21.1835C5.48434 21.1835 4.94587 21.3924 4.52588 21.7398V3.15135C4.9459 3.4987 5.48436 3.70766 6.07072 3.70766Z" fill="#003443"></path>
            <path d="M17.0384 5.71252H7.39844V7.38173H17.0384V5.71252Z" fill="#003443"></path>
            <path d="M17.0384 9.17908H7.39844V10.8483H17.0384V9.17908Z" fill="#003443"></path>
            <path d="M17.0384 12.6458H7.39844V14.315H17.0384V12.6458Z" fill="#003443"></path>
            <path d="M17.0384 17.6534H10.8755V19.3227H17.0384V17.6534Z" fill="#003443"></path>
          </g>
          <defs>
            <clipPath id="clip0_24_2044">
              <rect width="24" height="24" fill="white" transform="translate(0.106445 0.445557)"></rect>
            </clipPath>
          </defs>
        </svg>
      </div>
      <div class="flex-grow-0 flex-shrink-0 w-[123px] h-6">
        <p class="absolute left-11 top-[223px] text-xl text-left text-[#666]">Settings</p>
        <svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 absolute left-0 top-[223px]" preserveAspectRatio="xMidYMid meet">
          <g clip-path="url(#clip0_24_2053)">
            <path d="M19.5365 13.4256C19.5765 13.1056 19.6065 12.7856 19.6065 12.4456C19.6065 12.1056 19.5765 11.7856 19.5365 11.4656L21.6465 9.81556C21.8365 9.66556 21.8865 9.39556 21.7665 9.17556L19.7665 5.71556C19.6765 5.55556 19.5065 5.46556 19.3265 5.46556C19.2665 5.46556 19.2065 5.47556 19.1565 5.49556L16.6665 6.49556C16.1465 6.09556 15.5865 5.76556 14.9765 5.51556L14.5965 2.86556C14.5665 2.62556 14.3565 2.44556 14.1065 2.44556H10.1065C9.85653 2.44556 9.64653 2.62556 9.61653 2.86556L9.23653 5.51556C8.62653 5.76556 8.06653 6.10556 7.54653 6.49556L5.05653 5.49556C4.99653 5.47556 4.93653 5.46556 4.87653 5.46556C4.70653 5.46556 4.53653 5.55556 4.44653 5.71556L2.44653 9.17556C2.31653 9.39556 2.37653 9.66556 2.56653 9.81556L4.67653 11.4656C4.63653 11.7856 4.60653 12.1156 4.60653 12.4456C4.60653 12.7756 4.63653 13.1056 4.67653 13.4256L2.56653 15.0756C2.37653 15.2256 2.32653 15.4956 2.44653 15.7156L4.44653 19.1756C4.53653 19.3356 4.70653 19.4256 4.88653 19.4256C4.94653 19.4256 5.00653 19.4156 5.05653 19.3956L7.54653 18.3956C8.06653 18.7956 8.62653 19.1256 9.23653 19.3756L9.61653 22.0256C9.64653 22.2656 9.85653 22.4456 10.1065 22.4456H14.1065C14.3565 22.4456 14.5665 22.2656 14.5965 22.0256L14.9765 19.3756C15.5865 19.1256 16.1465 18.7856 16.6665 18.3956L19.1565 19.3956C19.2165 19.4156 19.2765 19.4256 19.3365 19.4256C19.5065 19.4256 19.6765 19.3356 19.7665 19.1756L21.7665 15.7156C21.8865 15.4956 21.8365 15.2256 21.6465 15.0756L19.5365 13.4256ZM17.5565 11.7156C17.5965 12.0256 17.6065 12.2356 17.6065 12.4456C17.6065 12.6556 17.5865 12.8756 17.5565 13.1756L17.4165 14.3056L18.3065 15.0056L19.3865 15.8456L18.6865 17.0556L17.4165 16.5456L16.3765 16.1256L15.4765 16.8056C15.0465 17.1256 14.6365 17.3656 14.2265 17.5356L13.1665 17.9656L13.0065 19.0956L12.8065 20.4456H11.4065L11.2165 19.0956L11.0565 17.9656L9.99653 17.5356C9.56653 17.3556 9.16653 17.1256 8.76653 16.8256L7.85653 16.1256L6.79653 16.5556L5.52653 17.0656L4.82653 15.8556L5.90653 15.0156L6.79653 14.3156L6.65653 13.1856C6.62653 12.8756 6.60653 12.6456 6.60653 12.4456C6.60653 12.2456 6.62653 12.0156 6.65653 11.7156L6.79653 10.5856L5.90653 9.88556L4.82653 9.04556L5.52653 7.83556L6.79653 8.34556L7.83653 8.76556L8.73653 8.08556C9.16653 7.76556 9.57653 7.52556 9.98653 7.35556L11.0465 6.92556L11.2065 5.79556L11.4065 4.44556H12.7965L12.9865 5.79556L13.1465 6.92556L14.2065 7.35556C14.6365 7.53556 15.0365 7.76556 15.4365 8.06556L16.3465 8.76556L17.4065 8.33556L18.6765 7.82556L19.3765 9.03556L18.3065 9.88556L17.4165 10.5856L17.5565 11.7156ZM12.1065 8.44556C9.89653 8.44556 8.10653 10.2356 8.10653 12.4456C8.10653 14.6556 9.89653 16.4456 12.1065 16.4456C14.3165 16.4456 16.1065 14.6556 16.1065 12.4456C16.1065 10.2356 14.3165 8.44556 12.1065 8.44556ZM12.1065 14.4456C11.0065 14.4456 10.1065 13.5456 10.1065 12.4456C10.1065 11.3456 11.0065 10.4456 12.1065 10.4456C13.2065 10.4456 14.1065 11.3456 14.1065 12.4456C14.1065 13.5456 13.2065 14.4456 12.1065 14.4456Z" fill="#666666"></path>
          </g>
          <defs>
            <clipPath id="clip0_24_2053">
              <rect width="24" height="24" fill="white" transform="translate(0.106445 0.445557)"></rect>
            </clipPath>
          </defs>
        </svg>
      </div>
      <div class="flex-grow-0 flex-shrink-0 w-[111px] h-6">
        <p class="absolute left-11 top-[297px] text-xl text-left text-[#666]">Logout</p>
        <svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 absolute left-0 top-[300px]" preserveAspectRatio="xMidYMid meet">
          <g clip-path="url(#clip0_24_2058)">
            <path d="M13.1064 17.4456L14.5064 16.0456L11.9064 13.4456L22.1064 13.4456V11.4456L11.9064 11.4456L14.5064 8.84556L13.1064 7.44556L8.10645 12.4456L13.1064 17.4456ZM4.10645 5.44556H12.1064L12.1064 3.44556H4.10645C3.00644 3.44556 2.10645 4.34556 2.10645 5.44556L2.10645 19.4456C2.10645 20.5456 3.00644 21.4456 4.10645 21.4456H12.1064V19.4456H4.10645L4.10645 5.44556Z" fill="#666666"></path>
          </g>
          <defs>
            <clipPath id="clip0_24_2058">
              <rect width="24" height="24" fill="white" transform="translate(24.1064 24.4456) rotate(-180)"></rect>
            </clipPath>
          </defs>
        </svg>
      </div>
    </div>
    <svg width="282" height="2" viewBox="0 0 282 2" fill="none" xmlns="http://www.w3.org/2000/svg" class="absolute left-[-0.5px] top-[67.9px]" preserveAspectRatio="xMidYMid meet">
      <line y1="0.903442" x2="281.872" y2="0.903442" stroke="#F9F9FB"></line>
    </svg>
  </div>
  <div class="w-[1043px] h-[745px]">
    <svg width="1091" height="793" viewBox="0 0 1091 793" fill="none" xmlns="http://www.w3.org/2000/svg" class="absolute left-[282px] top-[219.5px]" preserveAspectRatio="none">
      <g filter="url(#filter0_d_24_2061)">
        <path d="M24 23.7106C24 21.109 26.109 19 28.7106 19H1062.29C1064.89 19 1067 21.109 1067 23.7106V759.289C1067 761.891 1064.89 764 1062.29 764H28.7106C26.109 764 24 761.891 24 759.289V23.7106Z" fill="white"></path>
        <path d="M24.5 23.7106C24.5 21.3852 26.3852 19.5 28.7106 19.5H1062.29C1064.61 19.5 1066.5 21.3852 1066.5 23.7106V759.289C1066.5 761.615 1064.61 763.5 1062.29 763.5H28.7106C26.3851 763.5 24.5 761.615 24.5 759.289V23.7106Z" stroke="#BDBDBD" stroke-opacity="0.25"></path>
      </g>
      <defs>
        <filter id="filter0_d_24_2061" x="0.44688" y="0.157504" width="1090.11" height="792.106" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
          <feFlood flood-opacity="0" result="BackgroundImageFix"></feFlood>
          <feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha"></feColorMatrix>
          <feOffset dy="4.71062"></feOffset>
          <feGaussianBlur stdDeviation="11.7766"></feGaussianBlur>
          <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.07 0"></feColorMatrix>
          <feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow_24_2061"></feBlend>
          <feBlend mode="normal" in="SourceGraphic" in2="effect1_dropShadow_24_2061" result="shape"></feBlend>
        </filter>
      </defs>
    </svg>
    <%
      SQLConnection sql = new SQLConnectionFactory().getConnection();
      ResultSet rs = sql.executeQuery("SELECT * FROM transactions limit 20;");
      while (rs.next()) {
        Transactions t = new Transactions();
        t.mapFromResultSet(rs);
    %>
    <div class="w-[1011px] h-[34px] absolute left-[339px] top-[351px] overflow-hidden">
      <p class="w-24 h-[27px] absolute left-0 top-[3px] text-sm font-medium text-left text-black"> <%=t.getSenderAccount()%> </p>
      <p class="w-24 h-[27px] absolute left-[167px] top-[3px] text-sm font-medium text-left text-black"> <%=t.getTimestamp()%> </p>
      <p class="w-24 h-[27px] absolute left-[391px] top-[3px] text-sm font-medium text-left text-black"> <%=t.getTransactionName()%> </p>
      <p class="w-24 h-[27px] absolute left-[719px] top-[3px] text-sm font-medium text-left text-black"> <%=t.getAmount()%> </p>
      <svg width="1011" height="2" viewBox="0 0 1011 2" fill="none" xmlns="http://www.w3.org/2000/svg" class="absolute left-[-2px] top-[-2.24px]" preserveAspectRatio="none">
        <path d="M0 -0.241455H1091.74" stroke="#020202" stroke-opacity="0.6" stroke-width="3"></path>
      </svg>
    </div>
    <br>
    <%
      }
    %>
    <div class="w-[162px] h-[17px]">
      <p class="w-[162px] h-[17px] absolute left-[730px] top-[253px] text-sm font-semibold text-left uppercase text-neutral-600"> TRANSACTION NAME </p>
    </div>
    <div class="w-[37.79px] h-[16.6px]">
      <p class="w-[37.79px] h-[16.6px] absolute left-[339px] top-[253px] text-sm font-semibold text-left uppercase text-neutral-600"> SENDER </p>
    </div>
    <div class="w-[56.17px] h-[16.6px]">
      <p class="w-[56.17px] h-[16.6px] absolute left-[506px] top-[253px] text-sm font-semibold text-left uppercase text-neutral-600"> TIME </p>
    </div>
    <div class="w-[65.36px] h-[16.6px]">
      <p class="w-[65.36px] h-[16.6px] absolute left-[1058px] top-[253px] text-sm font-semibold text-left uppercase text-neutral-600"> AMOUNT </p>
    </div>
  </div>
  <p class="w-[59px] h-[17px] absolute left-[317px] top-[168px] text-sm font-medium text-left text-[#666]"> Sort by : </p>
  <div class="w-[196px] h-[34px] absolute left-[387px] top-40 overflow-hidden rounded bg-[#f9f9f9] border-[0.5px] border-[#dadada]">
    <p class="absolute left-2.5 top-1 text-sm font-medium text-left text-[#666]"> Type of Transaction </p>
    <svg width="10" height="6" viewBox="0 0 10 6" fill="none" xmlns="http://www.w3.org/2000/svg" class="absolute left-[179.25px] top-[13.25px]" preserveAspectRatio="xMidYMid meet">
      <path d="M1.42857 -3.74669e-07L5 3.57143L8.57143 -6.24448e-08L10 0.714286L5 5.71429L-3.12224e-08 0.714285L1.42857 -3.74669e-07Z" fill="#666666"></path>
    </svg>
  </div>
  <div class="w-[102px] h-[34px] absolute left-[1253px] top-[91px] overflow-hidden rounded bg-[#f9f9f9] border-[0.5px] border-[#dadada]">
    <div class="w-[42px] h-6">
      <p class="absolute left-3 top-[5px] text-sm font-medium text-left text-[#666]">Today</p>
    </div>
    <svg width="10" height="6" viewBox="0 0 10 6" fill="none" xmlns="http://www.w3.org/2000/svg" class="absolute left-[89.25px] top-[13.25px]" preserveAspectRatio="xMidYMid meet">
      <path d="M1.42857 -3.74669e-07L5 3.57143L8.57143 -6.24448e-08L10 0.714286L5 5.71429L-3.12224e-08 0.714285L1.42857 -3.74669e-07Z" fill="#666666"></path>
    </svg>
  </div>
</div>
</body>
</html>
