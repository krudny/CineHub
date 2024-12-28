import Navbar from "@/app/components/Navbar";
import { ReservationProps, Room, SeatProps } from "@/app/types/interfaces";
import SeatGenerator from "@/app/components/SeatGenerator";

async function getSeats(roomId: number) {
  const res = await fetch(`http://localhost:8080/room/seats/${roomId}`);
  return res.json();
}

export default async function Reservation({ searchParams }: ReservationProps) {
  const { title, fullDate, room } = searchParams || {};
  const parsedRoom: Room = room ? JSON.parse(room) : { roomId: null, name: "" };
  const [date, time] = fullDate.split("T");
  const seats: SeatProps[] = await getSeats(parsedRoom.roomId);

  console.log(seats);

  return (
    <div className="bg-zinc-900 max-w-screen min-h-screen text-neutral-100">
      <Navbar />
      <div className="container max-w-6xl mx-auto flex justify-center items-center flex-col">
        <div className="mt-10 flex flex-col items-center gap-y-4">
          <p className="text-5xl font-bold">{title}</p>
          <p className="text-2xl">
            {date} {time}
          </p>
          <p className="text-2xl">Room {parsedRoom.name}</p>
        </div>

        <div className="flex justify-end items-center mt-10">
          <div className="mt-1 w-56 h-0.5 bg-neutral-400"></div>
          <span className="text-neutral-400 text-2xl mx-3">Screen</span>
          <div className="mt-1 w-56 h-0.5 bg-neutral-400"></div>
        </div>

        <SeatGenerator seats={seats} />
      </div>
    </div>
  );
}
