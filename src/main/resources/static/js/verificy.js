window.onload=function()
{
    var count=30;  //设定限制时间是10秒
    var btn=document.getElementById("btn");
    var timer=null;  //定义计时器的名字
    btn.onclick=function()
    {
        clearInterval(timer);//先清除原来的计时器,或是设定一开始就是计时器启动前按钮不可用
        this.disabled=true; //按钮变成灰色，不可用，鼠标点击之后定时器开始前，按钮是不可以用的
        var that=this;//this指的就是按钮,把它赋给that
        timer=setInterval(fn,1000);  //开启计时器的名字，timer
        function fn()
        {
            count--;
            if(count>=0)
            {
                that.innerHTML=count+"s";  //之所以赋给that，就是为了防止这里误用this,这里的this指的是计时器，是window，是它调用了函数，不是button
            }
            else
            {
                that.innerHTML="重新发送短信"; //10秒钟时间到了就提示重新发送短信，注意双标签用innerHTML,单标签input框才是用value取值
                clearInterval(timer); //清除定时器，参数是定时器的名字
                count=30; //时间再次回到10秒
                that.disabled=false; //that就是按钮，按钮又变亮可以用了
            }
        }
    }
};