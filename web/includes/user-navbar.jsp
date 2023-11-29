<%-- 
    Document   : navbar
    Created on : 1 thg 7, 2023, 09:00:18
    Author     : phangiabao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="#"><img height="57px" src="https://danangjob.vn/Upload/Member/2023324144757.jpg"/></a>
    <!-- Sidebar Toggle-->
<!--    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>-->
    <c:set var="c" value="${sessionScope.userAuth}"/>
    <a class="nav-link fw-bold text-light" >
        <div class="sb-nav-link-icon "><i class="fas fa-users"></i> Welcome back: ${c.name}</div>

    </a>
   
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        <div class="input-group">
            &nbsp;
        </div>
    </form>
    
    <!-- Navbar-->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="user?action=view-profile">Profile</a></li>
                <li><a class="dropdown-item" href="user?action=change-password">Change Password</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li><a class="dropdown-item" href="login?action=logout">Logout</a></li>
            </ul>
        </li>
    </ul>
</nav>

<script>
    // Configs
    let liveChatBaseUrl   = document.location.protocol + '//' + 'livechat.fpt.ai/v36/src'
    let LiveChatSocketUrl = 'livechat.fpt.ai:443'
    let FptAppCode        = 'b959102a7ed00f933126885937774d50'
    let FptAppName        = 'Live support'
    // Define custom styles
    let CustomStyles = {
        // header
        headerBackground: 'linear-gradient(86.7deg, #C459F4FF 0.85%, #7E1CAAFF 98.94%)',
        headerTextColor: '#ffffffff',
        headerLogoEnable: true,
        headerLogoLink: 'https://chatbot-tools.fpt.ai/livechat-builder/img/theme/bank/logo.svg',
        headerText: 'Live support',
        // main
        primaryColor: '#9B34C9FF',
        secondaryColor: '#D8D8D8FF',
        primaryTextColor: '#ffffffff',
        secondaryTextColor: '#1F1F1FFF',
        buttonColor: '#9B34C9B3',
        buttonTextColor: '#ffffffff',
        bodyBackgroundEnable: true,
        bodyBackgroundLink: 'https://chatbot-tools.fpt.ai/livechat-builder/img/theme/bank/body.png',
        avatarBot: 'https://chatbot-tools.fpt.ai/livechat-builder/img/theme/bank/bot.svg',
        sendMessagePlaceholder: 'Enter your message here',
        // float button
        floatButtonLogo: 'https://chatbot-tools.fpt.ai/livechat-builder/img/theme/bank/coin.svg',
        floatButtonTooltip: 'Can I help you?',
        floatButtonTooltipEnable: true,
        // start screen
        customerLogo: 'https://chatbot-tools.fpt.ai/livechat-builder/img/theme/bank/logo.svg',
        customerWelcomeText: 'Please input your name',
        customerButtonText: 'Start',
        prefixEnable: false,
        prefixType: 'radio',
        prefixOptions: ["Anh","Chị"],
        prefixPlaceholder: 'Danh xưng',
        // custom css
        css: ''
    }
    // Get bot code from url if FptAppCode is empty
    if (!FptAppCode) {
        let appCodeFromHash = window.location.hash.substr(1)
        if (appCodeFromHash.length === 32) {
            FptAppCode = appCodeFromHash
        }
    }
    // Set Configs
    let FptLiveChatConfigs = {
        appName: FptAppName,
        appCode: FptAppCode,
        themes : '',
        styles : CustomStyles
    }
    // Append Script
    let FptLiveChatScript  = document.createElement('script')
    FptLiveChatScript.id   = 'fpt_ai_livechat_script'
    FptLiveChatScript.src  = liveChatBaseUrl + '/static/fptai-livechat.js'
    document.body.appendChild(FptLiveChatScript)
    // Append Stylesheet
    let FptLiveChatStyles  = document.createElement('link')
    FptLiveChatStyles.id   = 'fpt_ai_livechat_script'
    FptLiveChatStyles.rel  = 'stylesheet'
    FptLiveChatStyles.href = liveChatBaseUrl + '/static/fptai-livechat.css'
    document.body.appendChild(FptLiveChatStyles)
    // Init
    FptLiveChatScript.onload = function () {
        fpt_ai_render_chatbox(FptLiveChatConfigs, liveChatBaseUrl, LiveChatSocketUrl)
    }
</script>
