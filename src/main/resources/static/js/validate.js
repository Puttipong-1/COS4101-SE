var MicroModal = require('micromodal');
function checkPW(){
    var password=document.getElementById('password')
    var conpassword=document.getElementById('conpassword')
    if(password.value==conpassword.value) conpassword.setCustomValidity('');
    else conpassword.setCustomValidity('ยืนยันรหัสผ่านไม่ตรงกับรหัสผ่าน');
}
function checkImg(){
    var upload=document.getElementById('upload');
    var image=document.getElementById('image');
    var file=upload.files[0];
    var modal = new RModal(document.getElementById('modal'), {});
    var text=document.getElementById('modal-text');
    const path=upload.value;
    const ext= /(\.jpg|\.jpeg|\.png)$/i;
    if(!ext.exec(path)){
        text.textContent='รูปต้องเป็นนามสกุล .jpg .jpeg .png เท่านั้น';
        modal.open()
        upload.value='';
        image.src='';
        return false;
    }
    if(Math.round(file.size/1024)>3072){
        text.textContent='ไฟล์รูปต้องมีขนาดไม่เกิน 3 MB';
        modal.open()
        upload.value='';
        image.src='';
        return false;
    }
    var reader=new FileReader();
    reader.readAsDataURL(file);
    reader.onload=function (e){
        var img =new Image();
        img.src=e.target.result;
        img.onload = function (){
            var height=this.height;
            var width=this.width;
            if(height>600||width>600) {
                text.textContent='ขนาดของรูปต้องไม่เกิน 600*600 พิกเซล';
                modal.open();
                upload.value = '';
                image.src='';
                return false;
            }
            else{
                image.src=e.target.result;
            }
        }
    }
}
function checkTime(){
    var now=spacetime.now("Asia/Bangkok");
    var before=now.hour(10).minute(0).second(0).millisecond(0);
    var after=now.hour(20).minute(0).second(0).millisecond(0);
   if(now.isBetween(before,after)) {
        return true;
    }
    else {
        var modal = new RModal(document.getElementById('modal'), {});
        modal.open();
        return false;
    }
}
function closeModal(){
    var modal = new RModal(document.getElementById('modal'), {});
    modal.close();
}