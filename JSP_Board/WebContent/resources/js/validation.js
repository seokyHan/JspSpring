const id = document.querySelector('#id');
const password = document.querySelector('#pwd');
const passwordConfirm = document.querySelector('#pwdConfirm');
const memName = document.querySelector('#name');
const email = document.querySelector('#email');
const phone = document.querySelector('#phone');

initialize();

function initialize(){
    id.addEventListener('input', idValidation);
    password.addEventListener('input', passwordValidation);
    passwordConfirm.addEventListener('input', passwordCheck);
    memName.addEventListener('input', nameValidation);
    email.addEventListener('input', emailValidation);
    phone.addEventListener('input', phoneValidation);
}

function validationSuccess(element, text){
    const result = element.parentNode.children[1];
    result.innerHTML = text;
    result.style.color = 'green';
}

function validationFail(element, text){
    const result = element.parentNode.children[1];
    result.innerHTML = text;
    result.style.color = 'red';
}

function idValidation() {
    let idChk = false;
    const idValue = id.value.trim();
    const regId = /^[a-zA-Z][a-zA-Z0-9]{3,7}$/;

    if(regId.test(idValue)){
        idChk = true;
		idDuplicationCheck();
    }else if(idValue === ""){
        idChk = false;
        validationFail(id,'아이디를 입력하세요');
    }else{
        idChk = false;
        validationFail(id,'영문,숫자 4~8자리 입력 가능');
    }
}

function idDuplicationCheck(){
			
	$.ajax({
		url : 'idCheck',
		method : 'get',
		data : {'id' : id.value},
		success : function(result){
			if(result == "가능"){
				validationSuccess(id,'사용가능한 아이디 입니다.');
			}else{
				validationFail(id,'이미 존재하는 아이디 입니다.');
			}
		},
		error : function(xhr){
			alert(xhr.status);
		}
	})
			
}

function passwordValidation(){
    let pwdChk = false;
    const passwordValue = password.value.trim();
    const regPassword = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*()_+|]).{8,}$/;
    if(regPassword.test(passwordValue)){
        pwdChk = true;
        validationSuccess(password,"사용 가능한 패스워드 입니다!");
    }else if(passwordValue === ""){
        pwdChk = false;
        validationFail(password,"패스워드를 입력하세요");
    }else{
        pwdChk = false;
        validationFail(password,"대/소문자,특수문자,숫자 포함 8자리 이상 입력해야합니다!");
    }
}

function passwordCheck(){
    let pwdConChk = false;
    const passValue = password.value.trim();
    const passConValue = passwordConfirm.value.trim();
    
    if(passValue === passConValue){
        pwdConChk = true;
        validationSuccess(passwordConfirm,"비밀번호 일치");
    }else{
        pwdConChk = false;
        validationFail(passwordConfirm,"비밀번호 불일치");
    }
}

function nameValidation(){
    let nameChk = false;
    const nameValue = memName.value.trim();
    const regName = /^[가-힣]{2,5}$/;

    if(regName.test(nameValue)){
        nameChk = true;
        validationSuccess(memName, '');
    }else if(nameValue === ""){
        nameChk = false;
        validationFail(memName, '이름을 입력하세요');
    }else{
        nameChk = false;
        validationFail(memName, '형식에 맞게 작성해주세요');
    }
}

function emailValidation(){
    let emailChk = false;
    const emailValue = email.value.trim();
    const regEmail = 
;

    if(regEmail.test(emailValue)){
        emailChk = true;
        validationSuccess(email, '');
    }else if(emailValue === ""){
        emailChk = false;
        validationFail(email, '이메일을 입력하세요');
    }else{
        emailChk = false;
        validationFail(email, '형식에 맞게 이메일을 입력해주세요');
    }
}

function phoneValidation(){
    let phoneChk = false;
    const phoneValue = phone.value.trim();
    const regPhone = /^[0-9]{3}[0-9]{3,4}[0-9]{4}$/;

    if(regPhone.test(phoneValue)){
        phoneChk = false;
        validationSuccess(phone,'');
    }else if(phoneValue === ""){
        phoneChk = false
        validationFail(phone, '전화번호를 입력하세요');
    }else{
        phoneChk = false
        validationFail(phone, '형식에 맞게 전화번호를 입력해주세요');
    }
}

function findZip() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                let addr = ''; // 주소 변수
    
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
    
        
           document.getElementById("address").value = addr;
           
        }
    }).open();
}
