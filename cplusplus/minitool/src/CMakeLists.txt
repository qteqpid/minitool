
set(CMAKE_C_LINK_FLAGS "-nostdlib")

include_directories(
)

link_directories(
)

aux_source_directory(. minitool_src)

set( libs
)

set( test_libs
gtest
gtest_main
)

foreach( src ${minitool_src} )
    if( "${src}" MATCHES ".*test\\.cc$" )
	   list( APPEND minitool_test_src ${src} )
	else()
	   list( APPEND minitool_lib_src ${src} )
    endif() 
endforeach()

message( "Find lib src: " ${minitool_lib_src} )
message( "Find test src: " ${minitool_test_src} )

add_library( minitool ${minitool_lib_src})
target_link_libraries( minitool ${libs} )

# link test
add_executable( test ${minitool_test_src} ${minitool_src} )
target_link_libraries( test ${libs} ${test_libs} )

install(TARGETS minitool DESTINATION lib)
install(FILES encoder.h DESTINATION include)

 
