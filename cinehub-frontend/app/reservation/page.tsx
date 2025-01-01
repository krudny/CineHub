"use client";

import Navbar from "@/app/components/Navbar";
import { Room, SeatProps } from "@/app/types/interfaces";
import SeatGenerator from "@/app/components/SeatGenerator";
import { useEffect, useState } from "react";

export default function Reservation() {
  const [reservation, setReservation] = useState<{
    title: string;
    fullDate: string;
    room: Room;
  } | null>(null);
  const [seats, setSeats] = useState<SeatProps[] | null>(null);
  const [selectedSeat, setSelectedSeat] = useState<number | null>(null);
  const [takenSeats, setTaken] = useState<number[]>([1, 4, 5, 6, 8]);

  useEffect(() => {
    const storedReservation = sessionStorage.getItem("reservation");
    if (storedReservation) {
      setReservation(JSON.parse(storedReservation));
    }
  }, []);

  useEffect(() => {
    async function fetchSeats() {
      if (reservation?.room.roomId) {
        const res = await fetch(`http://localhost:8080/room/seats/${reservation.room.roomId}`);
        const data = await res.json();
        setSeats(data);
      }
    }
    if (reservation) {
      fetchSeats();
    }
  }, [reservation]);

  const pickSeatAction = (seatId: number) => {
    if(!takenSeats.includes(seatId)) {
      setSelectedSeat(seatId);
    }

  };

  if (!reservation) {
    return (
        <div className="bg-zinc-900 max-w-screen min-h-screen text-neutral-100 flex items-center justify-center">
          <p>Loading reservation...</p>
        </div>
    );
  }

  if (!seats) {
    return (
        <div className="bg-zinc-900 max-w-screen min-h-screen text-neutral-100 flex items-center justify-center">
          <p>Loading seats...</p>
        </div>
    );
  }

  const [date, time] = reservation.fullDate.split("T");

  return (
      <div className="bg-zinc-900 max-w-screen min-h-screen text-neutral-100">
        <Navbar />
        <div className="container max-w-6xl mx-auto flex justify-center items-center flex-col">
          <div className="mt-10 flex flex-col items-center gap-y-4">
            <p className="text-5xl font-bold">{reservation.title}</p>
            <p className="text-2xl">
              {date} {time}
            </p>
            <p className="text-2xl">Room {reservation.room.name}</p>
          </div>

          <div className="flex justify-end items-center mt-10">
            <div className="mt-1 w-56 h-0.5 bg-neutral-400"></div>
            <span className="text-neutral-400 text-2xl mx-3">Screen</span>
            <div className="mt-1 w-56 h-0.5 bg-neutral-400"></div>
          </div>

          <SeatGenerator seats={seats} pickSeatAction={pickSeatAction} selectedSeat={selectedSeat} takenSeats={takenSeats}/>
        </div>
      </div>
  );
}
