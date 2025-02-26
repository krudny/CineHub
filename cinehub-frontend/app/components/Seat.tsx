"use client";

import {SeatProps} from "@/app/types/interfaces";

export default function Seat({ seatId, seatNumber, isTaken, isSelected, pickSeatAction }: SeatProps) {
  const seatColor = isTaken ? "bg-neutral-600" : isSelected ? "bg-green-600" : "bg-zinc-900";

  return (
    <div className={`relative flex flex-col items-center justify-center w-20 h-20 cursor-pointer select-none`}>
      <div onClick={() => pickSeatAction({seatId: seatId, seatNumber: seatNumber})} className="flex flex-col items-center">
      <div
        className={`${seatColor} relative  w-12 h-12 border-2 border-neutral-100 rounded-md z-10 flex items-center justify-center`}
      >
        <span className="text-lg font-bold">{seatNumber}</span>
      </div>
      <div
        className={`${seatColor} relative w-16 h-5 border-2 border-neutral-100 rounded-sm -mt-3 z-0`}
      ></div>
      <div
        className={`${seatColor} absolute top-12 w-[44.16px] h-1  z-10`}
      ></div>
      </div>

    </div>
  );
}
