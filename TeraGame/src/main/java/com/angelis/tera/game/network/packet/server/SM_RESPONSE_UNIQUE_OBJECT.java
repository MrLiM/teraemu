package com.angelis.tera.game.network.packet.server;

import java.nio.ByteBuffer;

import com.angelis.tera.game.network.connection.TeraGameConnection;
import com.angelis.tera.game.network.packet.TeraServerPacket;

public class SM_RESPONSE_UNIQUE_OBJECT extends TeraServerPacket {

    @Override
    protected void writeImpl(TeraGameConnection connection, ByteBuffer byteBuffer) {
        // 8BFB080011000000000000000000001100A8003E008C00850D0A000000000088CB185100000000FF0BBD72000000006ACF951F00000000020000000056006F007400720065002000610063006800610074002000640061006E00730020006C006100200062006F007500740069007100750065002000640027006F0062006A0065006300740073000000470069006600740042006F007800300031002E0062006D0070000000A8003F01D5002301860D0A000000000089CB185100000000FF0BBD72000000006ACF951F00000000020000000056006F007400720065002000610063006800610074002000640061006E00730020006C006100200062006F007500740069007100750065002000640027006F0062006A0065006300740073000000470069006600740042006F007800300031002E0062006D00700000003F01D6016C01BA01870D0A000000000089CB185100000000FF0BBD72000000006ACF951F00000000020000000056006F007400720065002000610063006800610074002000640061006E00730020006C006100200062006F007500740069007100750065002000640027006F0062006A0065006300740073000000470069006600740042006F007800300031002E0062006D0070000000D6016D0203025102880D0A00000000008ACB185100000000FF0BBD72000000006ACF951F00000000020000000056006F007400720065002000610063006800610074002000640061006E00730020006C006100200062006F007500740069007100750065002000640027006F0062006A0065006300740073000000470069006600740042006F007800300031002E0062006D00700000006D0204039A02E802890D0A00000000008BCB185100000000FF0BBD72000000006ACF951F00000000010000000056006F007400720065002000610063006800610074002000640061006E00730020006C006100200062006F007500740069007100750065002000640027006F0062006A0065006300740073000000470069006600740042006F007800300031002E0062006D007000000004039B0331037F038A0D0A00000000008CCB185100000000FF0BBD72000000006ACF951F00000000020000000056006F007400720065002000610063006800610074002000640061006E00730020006C006100200062006F007500740069007100750065002000640027006F0062006A0065006300740073000000470069006600740042006F007800300031002E0062006D00700000009B033204C8031604C7D112000000000024473F5100000000FF0BBD72000000006ACF951F00000000020000000056006F007400720065002000610063006800610074002000640061006E00730020006C006100200062006F007500740069007100750065002000640027006F0062006A0065006300740073000000470069006600740042006F007800300031002E0062006D0070000000320400005F04AD045F25130000000000AA633F5100000000FF0BBD72000000006ACF951F00000000020000000056006F007400720065002000610063006800610074002000640061006E00730020006C006100200062006F007500740069007100750065002000640027006F0062006A0065006300740073000000470069006600740042006F007800300031002E0062006D0070000000
    }

}