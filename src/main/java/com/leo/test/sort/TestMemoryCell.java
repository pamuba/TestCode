package com.leo.test.sort;

public class TestMemoryCell
{
    public static void main( String [ ] args )
    {
        MemoryCell<Integer> m = new MemoryCell<Integer>( );

        m.write( 5 );
        System.out.println( "Contents are: " + m.read( ) );
    }
}
