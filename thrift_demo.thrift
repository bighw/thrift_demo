namespace java com.maizuo.thrift
struct Cinema {
    1:i32 cinemaId;
    2:string cinemaName;
    3:bool isUse;
    4:i32 hallCount;
}
service CinemaService {
    list<Cinema> getCinemas(1:i32 cinemaId , 2:i32 channelId);
}
