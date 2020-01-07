package sample;

import message.PostMsg2;
import utils.FormatData;

/**
 *
 * @author Abhishek M
 */
public class Test
{
    public static void servicecreate()
    {
        PostMsg2 msg = new PostMsg2();

        msg.setMsgType("0200");
        msg.setField(3, "510000");
        msg.setField(4, "0"); // INR 0.00
        msg.setField(11, "123456");
        msg.setField(22, "051"); // 051 - contact, 071 - contactless
        msg.setField(25, "00");
        msg.setField(35, "6000120000001234D20121260000004444");
        msg.setField(36, "1234567890abcde00001");
        msg.setField(41, "12345678");
        msg.setField(42, "MID000000000001");
        msg.setField(48, "D01234D112");
        msg.setField(49, "356");
        msg.setField(55, "82027C009F360200729F2701809F34031E03009F1E086D6639305F3031009F100706010A03A0A0009F3303E0E1C89F3501229F37044CCB7E229F0306000000000000810400000A1B9F02060000000025875F24032309305F25031809015F3401029F1A0203565F2A0203569F21031624099C01008E0E00000000000000001E0302031F009F0D05F8709C88009F0E0500000000009F4005FF80F000019F2608C04EBB8FD285FBA89F0702FF009A031912185F280207029F09023F009F4104000000009F0F05F8F8DC98005F201A202F202020202020202020202020202020202020202020202020950508800008009B02E8009F0607A0000000031010");
        
        System.out.println(msg.dumpMsg());
        
        String req = msg.getMsg();
        req = FormatData.resize(Integer.toString(req.length()), 4,"0",false) + req;
        
        String rsp = FormatData.sendAndRxHTTPMsg("https://europa-sandbox.perseuspay.com/isopos/processtransaction", 30000, 
                req, "text/plain");
        
        PostMsg2 msg_r = new PostMsg2();
        msg_r.parseMsgFromRemote(rsp);
        
        System.out.println(msg_r.dumpMsg());
    }

    public static void main(String[] args)
    {
        Test.servicecreate();
    }
}
