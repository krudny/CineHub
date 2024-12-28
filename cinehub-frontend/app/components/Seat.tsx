"use client";

import { useState } from "react";

export default function Seat({ seatNumber }: { seatNumber: number }) {
  const [isSelected, setIsSelected] = useState(false);

  const handleClick = () => {
    setIsSelected((prev) => !prev);
  };

  return (
    <div
      className={`relative flex flex-col items-center justify-center w-20 h-20 cursor-pointer select-none`}
      onClick={handleClick}
    >
      <div
        className={`${isSelected ? "bg-green-600" : "bg-transparent"} relative w-12 h-12 border-2 border-neutral-100 rounded-md z-10 flex items-center justify-center`}
      >
        <span className="text-lg font-bold">{seatNumber}</span>
      </div>
      <div
        className={`${isSelected ? "bg-green-600" : "bg-transparent"} relative w-16 h-5 border-2 border-neutral-100 rounded-sm -mt-3 z-0`}
      ></div>
      <div
        className={`${isSelected ? "bg-green-600" : "bg-zinc-900"} absolute top-12 w-[44.16px] h-1  z-10`}
      ></div>
    </div>
  );
}
