import Seat from "@/app/components/Seat";

export default function SeatGenerator({
  seats,
}: {
  seats: { seatId: number; seatNumber: number }[];
}) {
  const rows = [];
  const columnCounts = [5, 7, 9];
  let seatIndex = 0;

  for (const colCount of columnCounts) {
    if (seatIndex >= seats.length) break;

    const rowSeats = seats.slice(seatIndex, seatIndex + colCount);
    seatIndex += colCount;

    const gridClass =
      colCount === 5
        ? "grid-cols-5"
        : colCount === 7
          ? "grid-cols-7"
          : "grid-cols-9";

    rows.push(
      <div key={`row-${seatIndex}`} className={`grid ${gridClass} gap-1`}>
        {rowSeats.map((seat) => (
          <Seat key={seat.seatId} seatNumber={seat.seatNumber} />
        ))}
      </div>,
    );
  }

  if (seatIndex < seats.length) {
    rows.push(
      <div key="remaining" className="grid grid-cols-9 gap-1">
        {seats.slice(seatIndex).map((seat) => (
          <Seat key={seat.seatId} seatNumber={seat.seatNumber} />
        ))}
      </div>,
    );
  }

  return (
    <div className="mt-8 flex flex-col items-center pb-10">
      {rows.map((row, index) => (
        <div key={`row-container-${index}`} className="flex justify-center">
          {row}
        </div>
      ))}
    </div>
  );
}
