export default function Seat({
                               seatNumber,
                               isSelected,
                               onClick,
                             }: {
  seatNumber: number;
  isSelected: boolean;
  onClick: (seatNumber: number) => void;
}) {
  return (
      <div
          onClick={() => onClick(seatNumber)}
          className={`w-12 h-12 border-2 border-neutral-100 rounded-2xl flex justify-center items-center cursor-pointer 
      ${isSelected ? "bg-orange-500" : "bg-transparent"}`}
      >
        <span className="font-bold select-none">{seatNumber}</span>
      </div>
  );
}
