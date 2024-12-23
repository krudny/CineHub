"use client";

import { useState } from "react";
import Seat from "@/app/components/Seat";

export default function SeatsGrid() {
  const [selectedSeat, setSelectedSeat] = useState<number | null>(null);

  const handleSeatClick = (seatNumber: number) => {
    setSelectedSeat(seatNumber === selectedSeat ? null : seatNumber); // Toggle seat selection
  };

  const rows = 10;
  const cols = 10;
  let cnt = 0;

  const seats = Array.from({ length: rows }, (_, row) =>
    Array.from({ length: cols }, (_, col) => {
      cnt += 1;
      return (
        <Seat
          key={`${row + 1}-${col + 1}`}
          seatNumber={cnt}
          isSelected={selectedSeat === cnt}
          onClick={handleSeatClick}
        />
      );
    }),
  ).flat();

  return (
    <div className="w-fit p-2">
      <div className="bg-zinc-800 h-full rounded-xl p-6 text-neutral-100 flex flex-col justify-center items-center">
        <div className="text-lg center font-bold my-2">Screen</div>
        <div className="w-3/4 h-1 bg-white rounded-full shadow-md mx-auto mb-10"></div>
        <div className={`grid gap-x-6 gap-y-2 grid-cols-${cols}`}>{seats}</div>
      </div>
    </div>
  );
}
