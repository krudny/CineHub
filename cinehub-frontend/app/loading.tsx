export default function Loading() {
  return (
    <div className="fixed inset-0 flex items-center justify-center bg-zinc-800">
      <div className="animate-spin rounded-full h-16 w-16 border-t-4 border-orange-600 border-opacity-75"></div>
    </div>
  );
}
