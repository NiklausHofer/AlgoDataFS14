#define BOOST_TEST_MAIN

#include <iostream>
#include <string>
#include <stdexcept>

#include "Position.h"
#include "LinkedPosition.h"
#include "Listerface.h"
#include "LinkedList.h"
#include "InvalidPosition.h"

#include <boost/test/included/unit_test.hpp>

using namespace std;

LinkedList<string> * generateList()
{
  LinkedList<string> * list = new LinkedList<string>();

  list->insertFirst( new string( "first element" ) );
  list->insertLast( new string( "second element" ) );
  list->insertLast( new string( "third element" ) );
  list->insertLast( new string( "fourth element" ) );
  list->insertLast( new string( "fifth element" ) );

  return list;
}

BOOST_AUTO_TEST_CASE( init_test )
{
  LinkedList<int> * list = new LinkedList<int>();
  
  BOOST_CHECK( NULL == list->first() );
  BOOST_CHECK( NULL == list->last() );
  BOOST_CHECK_EQUAL( 0, list->getSize() );
}

BOOST_AUTO_TEST_CASE( pass_wrong_position_test )
{
  LinkedList<string> * list = generateList();
  InvalidPosition<string> * pos = new InvalidPosition<string>();

  string * str = new string( "foo bar" );

  BOOST_CHECK_THROW( list->next( pos ), runtime_error );
  BOOST_CHECK_THROW( list->previous( pos ), runtime_error );
  BOOST_CHECK_THROW( list->replaceElement( pos, str ), runtime_error );
  BOOST_CHECK_THROW( list->insertBefore( pos, str ), runtime_error );
  BOOST_CHECK_THROW( list->insertAfter( pos, str ), runtime_error );
  BOOST_CHECK_THROW( list->remove( pos ), runtime_error );
}

BOOST_AUTO_TEST_CASE( first_elem_to_empty_list_test )
{
  LinkedList<string> * list = new LinkedList<string>();
  string * str = new string( "Lorem ipsum dolor sit amet" );

  Position<string> * pos = list->insertFirst( str );

  BOOST_CHECK_EQUAL( 1, list->getSize() );
  BOOST_CHECK_EQUAL( str, pos->getElement() );
  BOOST_CHECK( list->isFirst( pos ) );
  BOOST_CHECK( list->isLast( pos ) );
  BOOST_CHECK_EQUAL( pos, list->first() );
  BOOST_CHECK_EQUAL( pos, list->last() );

  BOOST_CHECK( NULL == list->next( pos ) );
  BOOST_CHECK( NULL == list->previous( pos ) );
}

BOOST_AUTO_TEST_CASE( last_elem_to_empty_list_test )
{
  LinkedList<string> * list = new LinkedList<string>();
  string * str = new string( "Lorem ipsum dolor sit amet" );

  Position<string> * pos = list->insertLast( str );

  BOOST_CHECK_EQUAL( 1, list->getSize() );
  BOOST_CHECK_EQUAL( str, pos->getElement() );
  BOOST_CHECK( list->isFirst( pos ) );
  BOOST_CHECK( list->isLast( pos ) );
  BOOST_CHECK_EQUAL( pos, list->first() );
  BOOST_CHECK_EQUAL( pos, list->last() );

  BOOST_CHECK( NULL == list->next( pos ) );
  BOOST_CHECK( NULL == list->previous( pos ) );
}

BOOST_AUTO_TEST_CASE( remove_last_element_test )
{
  LinkedList<string> * list = new LinkedList<string>();
  string * str = new string( "Lorem ipsum dolor sit amet" );

  Position<string> * pos = list->insertLast( str );

  string * res = list->remove( pos );

  BOOST_CHECK_EQUAL( str, res );
  BOOST_CHECK_EQUAL( *(new string("Lorem ipsum dolor sit amet")), *res );
  BOOST_CHECK_EQUAL( 0, list->getSize() );

  BOOST_CHECK( NULL == list->first() );
  BOOST_CHECK( NULL == list->last() );
}

BOOST_AUTO_TEST_CASE( pass_object_from_other_list_test )
{
  LinkedList<string> * myList = generateList();
  LinkedList<string> * otherList = generateList();

  string * str = new string( "qux, baaz" );

  LinkedPosition<string> * pos = (LinkedPosition<string>*)( otherList->next( otherList->first() ) );

  BOOST_CHECK_THROW( myList->next( pos ), runtime_error );
  BOOST_CHECK_THROW( myList->previous( pos ), runtime_error );
  BOOST_CHECK_THROW( myList->replaceElement( pos, str ), runtime_error );
  BOOST_CHECK_THROW( myList->insertBefore( pos, str ), runtime_error );
  BOOST_CHECK_THROW( myList->insertAfter( pos, str ), runtime_error );
  BOOST_CHECK_THROW( myList->remove( pos ), runtime_error );
}

BOOST_AUTO_TEST_CASE( next_not_last_test )
{
  LinkedList<string> * list = generateList();

  LinkedPosition<string> * pos = (LinkedPosition<string>*)( list->next( list->first() ) );

  BOOST_CHECK_EQUAL( *( new string( "second element" ) ), *( pos->getElement() ) );
  BOOST_CHECK_EQUAL( *( new string( "third element" ) ), *( list->next( pos )->getElement() ) );
  BOOST_CHECK_EQUAL( pos, list->next( list->previous( pos ) ) );
  BOOST_CHECK_EQUAL( pos, list->previous( list->next( pos ) ) );
}

BOOST_AUTO_TEST_CASE( previous_not_first_test )
{
  LinkedList<string> * list = generateList();

  LinkedPosition<string> * pos1 = (LinkedPosition<string>*)( list->previous( list->last() ) );
  LinkedPosition<string> * pos2 = (LinkedPosition<string>*)( list->previous( pos1 ) );

  BOOST_CHECK_EQUAL( *( new string( "third element" ) ), *( pos2->getElement() ) );
  BOOST_CHECK_EQUAL( *( new string( "second element" ) ), *( list->previous( pos2 )->getElement() ) );
  BOOST_CHECK_EQUAL( pos2, list->next( list->previous( pos2 ) ) );
  BOOST_CHECK_EQUAL( pos2, list->previous( list->next( pos2 ) ) );
  BOOST_CHECK_EQUAL( list->next( pos2 ), pos1 );
  BOOST_CHECK_EQUAL( list->previous( pos1 ), pos2 );
}

BOOST_AUTO_TEST_CASE( previous_of_first_test )
{
  LinkedList<string> * list = generateList();

  LinkedPosition<string> * pos = (LinkedPosition<string>*)( list->first() );

  BOOST_CHECK( NULL == list->previous( pos ) );
}

BOOST_AUTO_TEST_CASE( next_of_last_test )
{
  LinkedList<string> * list = generateList();

  LinkedPosition<string> * pos = (LinkedPosition<string>*)( list->last() );

  BOOST_CHECK( NULL == list->next( pos ) );
}

BOOST_AUTO_TEST_CASE( remove_from_middle_test )
{
  LinkedList<string> * list = generateList();
  LinkedPosition<string> * pos = (LinkedPosition<string>*)( list->previous( list->previous( list->last() ) ) );
  LinkedPosition<string> * previous = (LinkedPosition<string>*)( list->previous( pos ) );
  LinkedPosition<string> * next = (LinkedPosition<string>*)( list->next( pos ) );

  BOOST_CHECK_EQUAL( 5, list->getSize() );

  string * str = list->remove( pos );

  BOOST_CHECK_EQUAL( 4, list->getSize() );
  BOOST_CHECK_EQUAL( *( new string( "third element" ) ), *str );
  BOOST_CHECK_EQUAL( list->next( previous ), next );
  BOOST_CHECK_EQUAL( list->previous( next ), previous );
}

BOOST_AUTO_TEST_CASE( remove_from_end_test )
{
  LinkedList<string> * list = generateList();
  LinkedPosition<string> * pos = (LinkedPosition<string>*)(   list->last() );
  LinkedPosition<string> * previous = (LinkedPosition<string>*)( list->previous( pos ) );

  BOOST_CHECK_EQUAL( 5, list->getSize() );

  string * str = list->remove( pos );

  BOOST_CHECK_EQUAL( 4, list->getSize() );
  BOOST_CHECK_EQUAL( *( new string( "fifth element" ) ), *str );
  BOOST_CHECK_EQUAL( previous, list->last() );
  BOOST_CHECK( NULL == list->next( previous ) );
}

BOOST_AUTO_TEST_CASE( remove_from_start_test )
{
  LinkedList<string> * list = generateList();
  LinkedPosition<string> * pos = (LinkedPosition<string>*)(   list->first() );
  LinkedPosition<string> * next = (LinkedPosition<string>*)( list->next( pos ) );

  BOOST_CHECK_EQUAL( 5, list->getSize() );

  string * str = list->remove( pos );

  BOOST_CHECK_EQUAL( 4, list->getSize() );
  BOOST_CHECK_EQUAL( *( new string( "first element" ) ), *str );
  BOOST_CHECK_EQUAL( next, list->first() );
  BOOST_CHECK( NULL == list->previous( next ) );
}

BOOST_AUTO_TEST_CASE( insert_last_test )
{
  LinkedList<string> * list = generateList();
  string * str = new string( "sixth element" );

  Position<string> * oldLast = list->last();

  BOOST_CHECK_EQUAL( 5, list->getSize() );

  Position<string> * newLast = list->insertLast( str );

  BOOST_CHECK_EQUAL( 6, list->getSize() );
  BOOST_CHECK_EQUAL( list->next( oldLast ), newLast );
  BOOST_CHECK_EQUAL( list->previous( newLast ), oldLast );
  BOOST_CHECK( NULL == list->next( newLast ) );
  BOOST_CHECK_EQUAL( str, newLast->getElement() );
}

BOOST_AUTO_TEST_CASE( insert_first_test )
{
  LinkedList<string> * list = generateList();
  string * str = new string( "zeroth element" );

  Position<string> * oldFirst = list->first();

  BOOST_CHECK_EQUAL( 5, list->getSize() );

  Position<string> * newFirst = list->insertFirst( str );

  BOOST_CHECK_EQUAL( 6, list->getSize() );
  BOOST_CHECK_EQUAL( list->next( newFirst ), oldFirst );
  BOOST_CHECK_EQUAL( list->previous( oldFirst ), newFirst );
  BOOST_CHECK( NULL == list->previous( newFirst ) );
  BOOST_CHECK_EQUAL( str, newFirst->getElement() );
}

BOOST_AUTO_TEST_CASE( insert_after_last_test )
{
  LinkedList<string> * list = generateList();
  string * str = new string( "sixth element" );

  Position<string> * oldLast = list->last();

  BOOST_CHECK_EQUAL( 5, list->getSize() );

  Position<string> * newLast = list->insertAfter( oldLast, str );

  BOOST_CHECK_EQUAL( 6, list->getSize() );
  BOOST_CHECK_EQUAL( list->next( oldLast ), newLast );
  BOOST_CHECK_EQUAL( list->previous( newLast ), oldLast );
  BOOST_CHECK( NULL == list->next( newLast ) );
  BOOST_CHECK_EQUAL( str, newLast->getElement() );
}

BOOST_AUTO_TEST_CASE( insert_before_first_test )
{
  LinkedList<string> * list = generateList();
  string * str = new string( "zeroth element" );

  Position<string> * oldFirst = list->first();

  BOOST_CHECK_EQUAL( 5, list->getSize() );

  Position<string> * newFirst = list->insertBefore( oldFirst, str );

  BOOST_CHECK_EQUAL( 6, list->getSize() );
  BOOST_CHECK_EQUAL( list->next( newFirst ), oldFirst );
  BOOST_CHECK_EQUAL( list->previous( oldFirst ), newFirst );
  BOOST_CHECK( NULL == list->previous( newFirst ) );
  BOOST_CHECK_EQUAL( str, newFirst->getElement() );
}

// TODO
// test replaceElement
// test insertBefore and insertAfter in the middle
